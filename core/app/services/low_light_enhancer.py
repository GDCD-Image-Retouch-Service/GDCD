import re
import sys
import os
import numpy as np
import cv2
import time
from PIL import Image

import torch
import torch.nn.functional as F

from models.mirnet_v2_arch import MIRNet_v2

import logging
logger = logging.getLogger(__name__)
logger.setLevel(logging.INFO)
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
stream_handler = logging.StreamHandler()
stream_handler.setFormatter(formatter)
logger.addHandler(stream_handler)
file_handler = logging.FileHandler('data/logs/core/optimization/low_light_enhancer.log')
file_handler.setFormatter(formatter)
logger.addHandler(file_handler)

class LowLightEnhancer():
    def __init__(self) -> None:
        try:
            self._factor = 4
            parameters = {
                'inp_channels': 3,
                'out_channels': 3,
                'n_feat': 80,
                'chan_factor': 1.5,
                'n_RRG': 4,
                'n_MRB': 2,
                'height': 3,
                'width': 2,
                'scale': 1,
            }
            self._device = torch.device(
                'cuda' if torch.cuda.is_available() else 'cpu')
            self._model = MIRNet_v2(**parameters)
            # self.model.load_state_dict(torch.load('models/enhancement_fivek.pth')['params'])
            self._model.load_state_dict(torch.load(
                'models/enhancement_lol.pth')['params'])
            self._model.to(self._device).eval()
            logger.info("MirnetV2 Successfully Build")

        except Exception as e:
            logger.error(f"Building MirnetV2 Failed !!! - {e}")

    # input image type is RGB
    def process(self, image: Image) -> np.float32:
        try:
            with torch.inference_mode():
                logger.info(f"Preprocessing {id(image)} ...")
                img = np.array(image)

                input_ = torch.from_numpy(img).float().div(
                    255.).permute(2, 0, 1).unsqueeze(0).to(self._device)

                # Padding in case images are not multiples of 4
                h, w = input_.shape[2], input_.shape[3]
                H, W = ((h+self._factor)//self._factor) * \
                    self._factor, ((w+self._factor)//self._factor)*self._factor
                padh = H-h if h % self._factor != 0 else 0
                padw = W-w if w % self._factor != 0 else 0
                input_ = F.pad(input_, (0, padw, 0, padh), 'reflect')
                logger.info(f"Preprocessing {id(image)} Complete")

                logger.info(f"Inference {id(image)} ...")
                restored = self._model(input_)
                logger.info(f"Inference {id(image)} Complete")

                logger.info(f"Postprocessing {id(image)} ...")
                # Unpad images to original dimensions
                restored = restored[:, :, :h, :w]

                restored = torch.clamp(restored, 0, 1).cpu(
                ).detach().permute(0, 2, 3, 1).squeeze(0).numpy()
                logger.info(f"Postprocessing {id(image)} Complete")

                return cv2.normalize(restored, None, 0, 255, cv2.NORM_MINMAX).astype(np.uint8)
        except Exception as e:
            logger.error(f"Enhancing Low Light Failed !!! - {id(image)} - {e}")
