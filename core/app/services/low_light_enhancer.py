import traceback

import numpy as np
import cv2
from PIL import Image

import torch
import torch.nn.functional as F

from models.mirnet_v2_arch import MIRNet_v2

from utils.utils import get_logger
logger = get_logger(__name__, 'data/logs/core/optimization')


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
            self._model.load_state_dict(torch.load(
                'models/saved/enhancement_lol.pth')['params'])
            self._model.to(self._device).eval()

            logger.info("MirnetV2 Successfully Build")
        except Exception as e:
            logger.error(f"Building MirnetV2 Failed !!! - {e} : {traceback.format_exc()}")
            raise e

    def _preprocess(self, image: Image):
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

        return input_, h, w

    def _postprocess(self, restored, h, w):
        # Unpad images to original dimensions
        restored = restored[:, :, :h, :w]
        restored = torch.clamp(restored, 0, 1).cpu(
        ).detach().permute(0, 2, 3, 1).squeeze(0).numpy()

        return cv2.normalize(restored, None, 0, 255, cv2.NORM_MINMAX).astype(np.uint8)

    def process(self, image: Image) -> np.ndarray:
        try:
            logger.info(f"Preprocessing {id(image)} ...")
            input_, h, w = self._preprocess(image)
            logger.info(f"Preprocessing {id(image)} Complete")
            
            with torch.inference_mode():
                logger.info(f"Inference {id(image)} ...")
                restored = self._model(input_)
                logger.info(f"Inference {id(image)} Complete")

            logger.info(f"Postprocessing {id(image)} ...")
            result = self._postprocess(restored, h, w)
            logger.info(f"Postprocessing {id(image)} Complete")

            return result
        except Exception as e:
            logger.error(f"Enhancing Low Light Failed !!! - {e} : {traceback.format_exc()}")
            raise e
