import traceback

import numpy as np

from PIL.Image import Image

import torch

from typing import List, Dict

from utils.general import check_img_size, non_max_suppression, scale_coords
from utils.datasets import letterbox
from models.experimental import attempt_load

from utils.utils import get_logger
logger = get_logger(__name__, 'data/logs/core/detection')


class Yolo():
    def __init__(self, det_path: str) -> None:
        try:
            self._device: str = torch.device("cuda" if torch.cuda.is_available() else "cpu")
            self._det_module = attempt_load(det_path, map_location=self._device)
            self._names = self._det_module.module.names if hasattr(self._det_module, 'module') else self._det_module.names
            self._stride = int(self._det_module.stride.max())

            logger.info("Yolo Successfully Build")
        except Exception as e:
            logger.error(f"Building Yolo Failed !!! - {e} : {traceback.format_exc()}")
            raise e
    
    def _preprocess(self, image: Image):
        img0 = np.array(image)
        imgsz = check_img_size(min(img0.shape[:2]), s=self._stride)  # check img_size

        img = letterbox(img0, imgsz, stride=self._stride)[0]
        img = img.transpose(2, 0, 1)
        img = np.ascontiguousarray(img)
        img = torch.from_numpy(img).to(self._device).float()
        img /= 255.0  # 0 - 255 to 0.0 - 1.0
        img = img.unsqueeze(0)

        return img0, img

    def _postprocess(self, output, img0, img):
        output[:, :4] = scale_coords(img.shape[2:], output[:, :4], img0.shape).round()
        line = []
        for *xyxy, conf, cls in reversed(output):
            x1, y1, x2, y2 = xyxy
            line.append({"class": str(self._names[int(cls)]), "ul": (int(x1), int(y1)), "dr": (int(x2), int(y2))})

        return line

    def predict(self, image: Image):
        try:
            logger.info(f"Preprocessing {id(image)} ...")
            img0, img = self._preprocess(image)
            logger.info(f"Preprocessing {id(image)} Complete")

            logger.info(f"Inference {id(image)} ...")
            with torch.no_grad():
                output = self._det_module(img, augment='store_true')[0]
                output = non_max_suppression(
                output, 0.25, 0.45, agnostic='store_true')[0]
            logger.info(f"Inference {id(image)} Complete")
            
            logger.info(f"PostProcessing {id(image)} ...")
            line = self._postprocess(output, img0, img)
            logger.info(f"PostProcessing {id(image)} Complete")

            return line            
        except Exception as e:
            logger.error(f"Detection Failed !!! - {id(image)} - {e} : {traceback.format_exc()}")
            raise e

