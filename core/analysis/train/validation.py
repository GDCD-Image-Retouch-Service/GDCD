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
    # load Dataset
    labels = json.load(open(args.labels, "r"))
    transform = transforms.Compose([
        transforms.Resize(342),
        transforms.RandomCrop(299),
        transforms.ToTensor(),
        transforms.Normalize(mean=[0.485, 0.456, 0.406],
                             std=[0.229, 0.224, 0.225])])
    dataset = AVADataset(labels, args.images, "jpg", transform)
    dataloader = torch.utils.data.DataLoader(dataset, batch_size=64,
                                             shuffle=True, num_workers=8)

    # load Model
    device = "cuda" if torch.cuda.is_available() else "cpu"
    nima = torch.jit.load(args.model, map_location=device).eval().to(device)

    # predict
    scores = []
    ids = []
    losses = []
    for batch in tqdm(dataloader):
        inputs = batch['image'].to(device)
        labels = batch['label']
        with torch.no_grad():
            outputs = nima(inputs).logits.cpu().detach().numpy()
        scores.extend([calc_mean_score(out) for out in outputs])
        ids.extend([id for id in batch['image_id']])
        losses.extend([single_emd_loss(torch.tensor(out), label).item()
                      for out, label in zip(outputs, labels)])
    result = {"ids": ids, "scores": scores, "losses": losses}
    with open(f"{args.save}.json", "w") as f:
        json.dump(result, f)


if __name__ == "__main__":
    parser = argparse.ArgumentParser()

    # model configurations
    parser.add_argument("--model", type=str)
    parser.add_argument("--labels", type=str)
    parser.add_argument("--images", type=str)
    parser.add_argument("--save", type=str)
    args = parser.parse_args()

    work(args)
