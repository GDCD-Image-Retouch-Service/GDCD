import torch
import torchvision.transforms as transforms
import numpy as np

from PIL.Image import Image
from typing import List, Dict

from utils.utils import calc_mean_score


class Nima():
    def __init__(self, aes_path: str, tec_path: str) -> None:
        super(Nima, self).__init__()
        self.device: str = "cuda" if torch.cuda.is_available() else "cpu"
        self.aes_module = torch.jit.load(
            aes_path, map_location=self.device).eval().to(self.device)
        self.tec_module = torch.jit.load(
            tec_path, map_location=self.device).eval().to(self.device)

        self.transform = transforms.Compose([
            transforms.Resize(360),
            transforms.RandomCrop(299),
            transforms.ToTensor(),
            transforms.Normalize(mean=[0.485, 0.456, 0.406],
                                 std=[0.229, 0.224, 0.225])])

    @torch.no_grad()
    @torch.inference_mode()
    def predict(self, imgs: List[Image]) -> List[Dict[str, float]]:
        torch_list = [self.transform(img) for img in imgs]
        inputs = torch.stack(
            [torch_img for torch_img in torch_list], dim=0).to(self.device)

        aes_outputs = self.aes_module(inputs).logits.detach().numpy()
        tec_outputs = self.tec_module(inputs).logits.detach().numpy()

        return [{"aesthetic": round(calc_mean_score(aes_out), 4),
                 "quality": round(calc_mean_score(tec_out), 4)}
                for aes_out, tec_out in zip(aes_outputs, tec_outputs)]
