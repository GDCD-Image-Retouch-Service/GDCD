import argparse
import os
import json
from tqdm import tqdm

import torch
import torchvision.transforms as transforms

from nima.data_generator import AVADataset
from nima.utils import calc_mean_score
from nima.loss import single_emd_loss

import warnings
warnings.filterwarnings(action='ignore')


def work(args):
    if args.type == "aesthetic":
        label_path = "./labels/ava_labels_test.json" if args.test else "./labels/ava_labels_train.json"
        images = "./dataset/AVA-dataset/images"
        image_type = "jpg"
    else:
        label_path = "./labels/tid_labels_test.json" if args.test else "./labels/tid_labels_train.json"
        images = "./dataset/TID2013/distorted_images"
        image_type = "bmp"
    
    if args.base == "InceptionV3":
        size = 342
        crop = 299
    else:
        size = 232
        crop = 224

    # load Dataset
    labels = json.load(open(label_path, "r"))
    transform = transforms.Compose([
        transforms.Resize(size),
        transforms.CenterCrop(crop),
        transforms.ToTensor(),
        transforms.Normalize(mean=[0.485, 0.456, 0.406],
                             std=[0.229, 0.224, 0.225])])
    dataset = AVADataset(labels, images, image_type, transform)
    dataloader = torch.utils.data.DataLoader(dataset, batch_size=64,
                                             shuffle=True, num_workers=8)

    # load Model
    device = "cuda" if torch.cuda.is_available() else "cpu"
    nima = torch.jit.load(args.model, map_location=device).eval().to(device)

    # predict
    scores = []
    ids = []
    losses = []
    grounds = []
    for batch in tqdm(dataloader):
        inputs = batch['image'].to(device)
        labels = batch['label']
        with torch.no_grad():
            outputs = nima(inputs).logits.cpu().detach().numpy()
        scores.extend([calc_mean_score(out) for out in outputs])
        ids.extend([id for id in batch['image_id']])
        losses.extend([single_emd_loss(torch.tensor(out), label).item()
                      for out, label in zip(outputs, labels)])
        grounds.extend([calc_mean_score(label.numpy()) for label in labels])
    result = {"ids": ids, "scores": scores, "losses": losses, "grounds": grounds}
    save = "test" if args.test else "train"
    with open(f"{args.type}_{args.base}_{save}.json", "w") as f:
        json.dump(result, f)


if __name__ == "__main__":
    parser = argparse.ArgumentParser()

    # model configurations
    parser.add_argument("--model", type=str)
    parser.add_argument("--base", type=str)
    parser.add_argument("--type", type=str)
    parser.add_argument("--test", type=bool)
    args = parser.parse_args()

    work(args)
