from torch.utils.tensorboard import SummaryWriter
import torchvision.transforms as transforms
import torch.optim as optim
import torch

from nima.data_generator import AVADataset, get_transform
from nima.model import Nima
from nima.loss import emd_loss
import nima.utils as utils

import argparse
import os
import json
from argparse import Namespace
from tqdm import tqdm
from datetime import datetime
import warnings
warnings.filterwarnings(action='ignore')


def work(args):
    config = Namespace(**json.load(open(args.config)))

    train_labels = utils.load_json(config.train_labels)
    test_labels = utils.load_json(config.test_labels)

    dt_now = datetime.now().strftime("%Y%m%d_%H_%M_%S")
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    ckpt_path = f"./models/{config.type}_{args.base_model_name}_{dt_now}/"
    summary_path = f"{ckpt_path}/tensorboard"
    if not os.path.exists(ckpt_path):
        os.makedirs(ckpt_path)

    # transform
    train_transform, val_transform = get_transform(args.base_model_name)

    # data loader
    trainset = AVADataset(
        labels=train_labels, image_dir=config.image_path, image_type=config.image_type, transform=train_transform)
    valset = AVADataset(
        labels=test_labels, image_dir=config.image_path, image_type=config.image_type, transform=val_transform)

    train_loader = torch.utils.data.DataLoader(trainset, batch_size=config.batch_size,
                                               shuffle=True, num_workers=config.num_workers)
    val_loader = torch.utils.data.DataLoader(valset, batch_size=config.batch_size,
                                             shuffle=False, num_workers=config.num_workers)

    # load model
    nima = torch.nn.DataParallel(Nima(base_model_name=args.base_model_name,
                                      dropout_rate=config.dropout_rate))
    nima.to(device)

    # Tensorboard
    writer = SummaryWriter(summary_path)

    init_val_loss = float('inf')
    train_losses = []
    val_losses = []

    # Train fc Layer start ------------------------------------------------------
    # Freeze Parameters except fc layer
    nima.module.freeze_only_base_module()

    # Optimizer fc
    optimizer_fc = optim.SGD(params=nima.module.base_module.parameters(),
                             lr=config.learning_rate_fc,
                             momentum=0.9
                             )

    # Scheduler
    scheduler_fc = optim.lr_scheduler.ExponentialLR(
        optimizer=optimizer_fc, gamma=config.decay)

    for epoch in range(1, config.epochs_train_fc + 1):
        # train
        with tqdm(train_loader, unit="batch") as tepoch:
            batch_losses = []
            for i, data in enumerate(tepoch):
                images = data['image'].to(device)
                labels_tensor = torch.unsqueeze(
                    data['label'].to(device).float(), 2)

                outputs = nima(images)
                outputs = outputs.view(-1, 10, 1)

                optimizer_fc.zero_grad()

                loss = emd_loss(labels_tensor, outputs)
                batch_losses.append(loss.item())
                loss.backward()

                optimizer_fc.step()

                tepoch.set_postfix(loss=loss.item())
                writer.add_scalar(
                    'batch train loss', loss.item(), i + epoch * (len(trainset) // config.batch_size + 1))

            avg_loss = sum(batch_losses) / \
                (len(trainset) // config.batch_size + 1)
            train_losses.append(avg_loss)
            print('Epoch %d mean training EMD loss: %.4f' % (epoch, avg_loss))

        scheduler_fc.step()

        # validate
        with tqdm(val_loader, unit="batch") as tepoch:
            batch_val_losses = []
            for data in tepoch:
                images = data['image'].to(device)
                labels_tensor = torch.unsqueeze(
                    data['label'].to(device).float(), 2)
                with torch.no_grad():
                    outputs = nima(images)
                outputs = outputs.view(-1, 10, 1)
                val_loss = emd_loss(labels_tensor, outputs)
                batch_val_losses.append(val_loss.item())
                tepoch.set_postfix(loss=val_loss.item())

            avg_val_loss = sum(batch_val_losses) / \
                (len(valset) // config.batch_size + 1)
            val_losses.append(avg_val_loss)
            print('Epoch %d completed. Mean EMD loss on val set: %.4f.' %
                  (epoch, avg_val_loss))
            writer.add_scalars('epoch losses', {
                'epoch train loss': avg_loss, 'epoch val loss': avg_val_loss}, epoch)

        # save model weights if val loss decreases
        if avg_val_loss < init_val_loss:
            init_val_loss = avg_val_loss
            print('Saving model...')
            torch.save(nima.module.base_module, os.path.join(
                ckpt_path, 'epoch-%d-%.4f.pt' % (epoch, avg_val_loss)))
            print('Done.\n')
    # Train fc Layer end ---------------------------------------------------------

    ##############################################################################

    # Train all Layer start ------------------------------------------------------
    # Unfreeze every Parameters
    nima.module.unfreeze_all()

    # Optimizer all
    optimizer_all = optim.SGD(params=nima.module.base_module.parameters(),
                              lr=config.learning_rate_all,
                              momentum=0.9
                              )

    # Scheduler all
    scheduler_all = optim.lr_scheduler.ExponentialLR(
        optimizer=optimizer_all, gamma=config.decay)

    # Train all Layer
    for epoch in range(config.epochs_train_fc + 1, config.epochs_train_fc + config.epochs_train_all + 1):
        with tqdm(train_loader) as tepoch:
            batch_losses = []
            for i, data in enumerate(tepoch):
                images = data['image'].to(device)
                labels_tensor = torch.unsqueeze(
                    data['label'].to(device).float(), 2)

                outputs = nima(images)
                outputs = outputs.view(-1, 10, 1)

                optimizer_all.zero_grad()

                loss = emd_loss(labels_tensor, outputs)
                batch_losses.append(loss.item())
                loss.backward()

                optimizer_all.step()

                tepoch.set_postfix(loss=loss.item())
                writer.add_scalar(
                    'batch train loss', loss.item(), i + epoch * (len(trainset) // config.batch_size + 1))

        avg_loss = sum(batch_losses) / (len(trainset) // config.batch_size + 1)
        train_losses.append(avg_loss)
        print('Epoch %d mean training EMD loss: %.4f' % (epoch, avg_loss))

        scheduler_all.step()

        with tqdm(val_loader, unit="batch") as tepoch:
            batch_val_losses = []
            for data in val_loader:
                images = data['image'].to(device)
                labels_tensor = torch.unsqueeze(
                    data['label'].to(device).float(), 2)
                with torch.no_grad():
                    outputs = nima(images)
                outputs = outputs.view(-1, 10, 1)
                val_loss = emd_loss(labels_tensor, outputs)
                batch_val_losses.append(val_loss.item())
                tepoch.set_postfix(loss=val_loss.item())

            avg_val_loss = sum(batch_val_losses) / \
                (len(valset) // config.batch_size + 1)
            val_losses.append(avg_val_loss)
            print('Epoch %d completed. Mean EMD loss on val set: %.4f.' %
                  (epoch, avg_val_loss))
            writer.add_scalars('epoch losses', {
                'epoch train loss': avg_loss, 'epoch val loss': avg_val_loss}, epoch)

        # Use early stopping to monitor training
        if avg_val_loss < init_val_loss:
            init_val_loss = avg_val_loss
            print('Saving model...')
            if not os.path.exists(ckpt_path):
                os.makedirs(ckpt_path)
            torch.save(nima.module.base_module, os.path.join(
                ckpt_path, 'epoch-%d-%.4f.pt' % (epoch, avg_val_loss)))
            print('Done.\n')
    # Train all Layer end ------------------------------------------------------


if __name__ == "__main__":
    parser = argparse.ArgumentParser()

    # model configurations
    parser.add_argument("--config", type=str,
                        default="configs/config_aesthetic.json")
    parser.add_argument("--base_model_name", type=str, default="InceptionV3")
    args = parser.parse_args()

    work(args)

    print('Training completed.')
