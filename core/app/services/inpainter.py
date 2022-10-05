import traceback

import numpy as np
import cv2
from PIL import Image

import torch
from torch.utils.data._utils.collate import default_collate

from typing import List, Dict

from models.lama import load_checkpoint
from utils.lama_datasets import move_to_device, pad_img_to_modulo
from utils.utils import get_logger
logger = get_logger(__name__, 'data/logs/core/optimization')


class Inpainter():
    def __init__(self, model_path: str) -> None:
        try:
            super().__init__()
            self._device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
            self._model = load_checkpoint(model_path, strict=False, map_location=self._device).to(self._device).eval()
            self._model.freeze()

            logger.info("Inpainter Successfully Build")
        except Exception as e:
            logger.error(f"Building Inpainter Failed !!! - {e} : {traceback.format_exc()}")
            raise e

    def _preprocess(self, image: Image.Image, points: List) -> Dict:
        img0 = np.array(image)

        img = np.transpose(np.array(img0), (2, 0, 1))
        img = img.astype('float32') / 255

        mask = np.zeros((img0.shape[0], img0.shape[1]))
        for point in points:
            cv2.rectangle(mask, (point[0], point[1]), (point[2], point[3]), 1, -1)
        mask = mask.astype('float32') / 255

        batch = dict(img=img, mask=mask[None, ...])
        batch['unpad_to_size'] = batch['img'].shape[1:]
        batch['img'] = pad_img_to_modulo(batch['img'], 8)
        batch['mask'] = pad_img_to_modulo(batch['mask'], 8)

        batch = default_collate([batch])
        batch = move_to_device(batch, self._device)
        batch['mask'] = (batch['mask'] > 0) * 1

        return batch

    def _postprocess(self, result: Dict) -> np.ndarray:
        cur_res = result['inpainted'][0].permute(
                    1, 2, 0).detach().cpu().numpy()
                    
        orig_height, orig_width = result.get('unpad_to_size', None)
        cur_res = cur_res[:orig_height, :orig_width]
        cur_res = np.clip(cur_res * 255, 0, 255).astype('uint8')
        
        return cur_res

    def process(self, image: Image.Image, points: List):
        try:
            logger.info(f"Preprocessing {id(image)} ...")
            batch = self._preprocess(image, points)
            logger.info(f"Preprocessing {id(image)} Complete")

            with torch.no_grad():
                logger.info(f"Inferencing {id(image)} ...")
                result = self._model(batch)
                logger.info(f"Inferencing {id(image)} Complete")

            logger.info(f"Postprocessing {id(image)} ...")
            result_img = self._postprocess(result)
            logger.info(f"Postprocessing {id(image)} Complete")

            return result_img
        except Exception as e:
            logger.error(f"Inpainting Failed !!! - {id(image)} - {e} : {traceback.format_exc()}")
            raise e
