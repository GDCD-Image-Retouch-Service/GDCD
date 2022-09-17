import os
from PIL import Image
import nima.utils as utils
from torch.utils import data
import torch


class AVADataset(data.Dataset):
    def __init__(self, labels: list, image_dir: str, image_type: str, transform=None):
        self.labels = labels
        self.image = image_dir
        self.image_type = image_type
        self.transform = transform

    def __len__(self):
        return len(self.labels)

    def __getitem__(self, idx):
        image_id = str(self.labels[idx]['image_id'])
        image = Image.open(os.path.join(
            self.image, image_id + f'.{self.image_type}')).convert('RGB')
        label = torch.tensor(utils.normalize_labels(self.labels[idx]['label']))
        sample = {'image_id': image_id, 'image': image, 'label': label}

        if self.transform:
            sample['image'] = self.transform(sample['image'])

        return sample
