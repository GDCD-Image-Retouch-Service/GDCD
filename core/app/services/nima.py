import traceback

from PIL.Image import Image

from typing import List, Dict

import torch
import torchvision.transforms as transforms

from utils.utils import calc_mean_score, get_logger
logger = get_logger(__name__, 'data/logs/core/scoring')


class Nima():
    def __init__(self, aes_path: str, tec_path: str) -> None:
        try:
            self._device: str = torch.device("cuda" if torch.cuda.is_available() else "cpu")
            self._aes_module = torch.jit.load(
                aes_path, map_location=self._device).eval().to(self._device)
            self._tec_module = torch.jit.load(
                tec_path, map_location=self._device).eval().to(self._device)

            self._transform = transforms.Compose([
                transforms.Resize(342),
                transforms.CenterCrop(299),
                transforms.ToTensor(),
                transforms.Normalize(mean=[0.485, 0.456, 0.406],
                                    std=[0.229, 0.224, 0.225])])
            
            logger.info("Nima Successfully Build")
        except Exception as e:
            logger.error(f"Building Nima Failed !!! - {e} : {traceback.format_exc()}")
            raise e

    def _preprocess(self, images: List[Image]) -> List[torch.Tensor]:
        torch_list = [self._transform(img) for img in images]
        input_ = torch.stack(
            [torch_img for torch_img in torch_list], dim=0).to(self._device)

        return input_

    def _postprocess(self, aes_output, tec_output) -> List[Dict[str, float]]:
        return [{"aesthetic": round(calc_mean_score(aes_out), 4),
                "quality": round(calc_mean_score(tec_out), 4)}
                for aes_out, tec_out in zip(aes_output, tec_output)]

    def process(self, images: List[Image]) -> List[Dict[str, float]]:
        try:
            logger.info(f"Preprocessing {id(images)} {len(images)} images ...")
            input_ = self._preprocess(images)
            logger.info(f"Preprocessing {id(images)} Complete")

            with torch.no_grad():
                logger.info(f"Inference {id(images)} ...")
                aes_output = self._aes_module(input_).logits.detach().numpy()
                tec_output = self._tec_module(input_).logits.detach().numpy()
                logger.info(f"Inference {id(images)} Complete")

            logger.info(f"Postprocessing {id(images)} ...")
            result = self._postprocess(aes_output, tec_output)
            logger.info(f"Postprocessing {id(images)} Complete")
            
            return result
        except Exception as e:
            logger.error(f"Predicting Failed!!! - {id(images)} - {e} : {traceback.format_exc()}")
            raise e
