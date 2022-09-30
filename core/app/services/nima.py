import torch
import torchvision.transforms as transforms
import numpy as np

from PIL.Image import Image
from typing import List, Dict

from utils.utils import calc_mean_score

import logging
logger = logging.getLogger(__name__)
logger.setLevel(logging.INFO)
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
stream_handler = logging.StreamHandler()
stream_handler.setFormatter(formatter)
logger.addHandler(stream_handler)
file_handler = logging.FileHandler('data/logs/core/scoring/nima.log')
file_handler.setFormatter(formatter)
logger.addHandler(file_handler)


class Nima():
    def __init__(self, aes_path: str, tec_path: str) -> None:
        try:
            self.device: str = "cuda" if torch.cuda.is_available() else "cpu"
            self.aes_module = torch.jit.load(
                aes_path, map_location=self.device).eval().to(self.device)
            self.tec_module = torch.jit.load(
                tec_path, map_location=self.device).eval().to(self.device)

            self.transform = transforms.Compose([
                transforms.Resize(342),
                transforms.CenterCrop(299),
                transforms.ToTensor(),
                transforms.Normalize(mean=[0.485, 0.456, 0.406],
                                    std=[0.229, 0.224, 0.225])])
            
            logger.info("Nima Successfully Build")
        except Exception as e:
            logger.error(f"Building Nima Failed !!! - {e}")

    @torch.no_grad()
    @torch.inference_mode()
    def predict(self, images: List[Image]) -> List[Dict[str, float]]:
        try:
            logger.info(f"Predicting {id(images)} {len(images)} images")
            torch_list = [self.transform(img) for img in images]
            inputs = torch.stack(
                [torch_img for torch_img in torch_list], dim=0).to(self.device)

            with torch.no_grad():
                aes_outputs = self.aes_module(inputs).logits.detach().numpy()
                tec_outputs = self.tec_module(inputs).logits.detach().numpy()

            logger.info(f"Predicting {id(images)} complete")
            return [{"aesthetic": round(calc_mean_score(aes_out), 4),
                    "quality": round(calc_mean_score(tec_out), 4)}
                    for aes_out, tec_out in zip(aes_outputs, tec_outputs)]
        except Exception as e:
            logger.error(f"Predicting Failed!!! - {id(images)} - {e}")
