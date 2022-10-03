import sys
import os
sys.path.append(os.path.dirname(os.path.abspath(os.path.dirname(__file__))))

import numpy as np
from PIL import Image
import cv2

import torch
from torch.utils.data._utils.collate import default_collate

from typing import List

from models.lama import load_checkpoint
from utils.lama_datasets import move_to_device, pad_img_to_modulo


os.makedirs('data/logs/core/optimization', exist_ok=True)
import traceback
import logging
logger = logging.getLogger(__name__)
logger.setLevel(logging.INFO)
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
stream_handler = logging.StreamHandler()
stream_handler.setFormatter(formatter)
logger.addHandler(stream_handler)
file_handler = logging.FileHandler('data/logs/core/optimization/inpainter.log')
file_handler.setFormatter(formatter)
logger.addHandler(file_handler)


class Inpainter():
    def __init__(self, model_path: str) -> None:
        try:
            super().__init__()
            self.device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

            self.model = load_checkpoint(model_path, strict=False, map_location=self.device).to(self.device).eval()
            self.model.freeze()

            logger.info("Inpainter Successfully Build")
        except Exception as e:
            logger.error(f"Building Inpainter Failed !!! - {e}")

    def _preprocess(self, image: Image.Image, points: List):
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

        return default_collate([batch])

    def process(self, image: Image.Image, points: List):
        try:
            logger.info(f"Preprocessing {id(image)} ...")
            
            batch = self._preprocess(image, points)

            logger.info(f"Preprocessing {id(image)} Complete")

            with torch.no_grad():
                logger.info(f"Inferencing {id(image)} ...")
                batch = move_to_device(batch, self.device)
                batch['mask'] = (batch['mask'] > 0) * 1
                batch = self.model(batch)
                logger.info(f"Inferencing {id(image)} Complete")

                logger.info(f"Postprocessing {id(image)} ...")
                cur_res = batch['inpainted'][0].permute(
                    1, 2, 0).detach().cpu().numpy()
                    
                orig_height, orig_width = batch.get('unpad_to_size', None)
                cur_res = cur_res[:orig_height, :orig_width]
                cur_res = np.clip(cur_res * 255, 0, 255).astype('uint8')
                logger.info(f"Postprocessing {id(image)} Complete")

            return cur_res
        except Exception as e:
            logger.error(f"Inpainting Failed !!! - {id(image)} - {e} : {traceback.format_exc()}")


if __name__ == "__main__":
    inpainter = Inpainter(model_path="models/best.ckpt")
    import json
    points_json = json.loads('[{"class":"person","ul":[227,370],"dr":[236,389]},{"class":"bench","ul":[297,363],"dr":[368,392]},{"class":"potted plant","ul":[120,377],"dr":[153,397]},{"class":"potted plant","ul":[236,377],"dr":[273,401]},{"class":"potted plant","ul":[387,378],"dr":[432,406]},{"class":"person","ul":[504,349],"dr":[529,402]},{"class":"potted plant","ul":[472,401],"dr":[560,454]}]')
    points = []
    for point in points_json:
        points.append(point['ul'] + point['dr'])
    print(points)
    exit()
    image = Image.open("IMG_0110.JPG").convert("RGB")
    # image = Image.open("IMG_0109.JPG").convert("RGB")
    result = inpainter.process(image, points)
    cv2.imshow("", cv2.cvtColor(result, cv2.COLOR_RGB2BGR))
    cv2.waitKey(0)
