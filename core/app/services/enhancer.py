import os
from typing import Dict
from PIL import Image
import cv2
import numpy as np

from services.low_light_enhancer import LowLightEnhancer

from utils.histogram import dynamic_hist_equal, hist_equal_ycrcb, hist_stretch_ycrcb, hist_equal_hsv
from utils.contrast import Ying_2017_CAIP


class Enhancer():
    def __init__(self, low_light_threshold=42) -> None:
        self._low_enhancer = LowLightEnhancer()
        self._threshold = low_light_threshold

    def _check_brightness(self, image: Image):
        hsv = cv2.cvtColor(np.array(image), cv2.COLOR_RGB2HSV)
        return hsv[..., 2].mean() < self._threshold

    def process(self, image: Image) -> dict:
        img = np.array(image)

        if self._check_brightness(img):
            img = self._low_enhancer.process(img)
            img = cv2.normalize(img, None, 0, 255,
                                cv2.NORM_MINMAX).astype(np.uint8)

        dynamic = dynamic_hist_equal(img)
        contrast = Ying_2017_CAIP(img)
        ycrcb_equal = hist_equal_ycrcb(img)
        ycrcb_stretch = hist_stretch_ycrcb(img)
        hsv_equal_s = hist_equal_hsv(img, on="s")
        hsv_equal_v = hist_equal_hsv(img, on="v")
        hsv_equal_sv = hist_equal_hsv(img, on="sv")

        return {"dynamic": dynamic, "contrast": contrast, "ycrcb_equal": ycrcb_equal, "ycrcb_stretch": ycrcb_stretch,
                "hsv_equal_s": hsv_equal_s, "hsv_equal_v": hsv_equal_v, "hsv_equal_sv": hsv_equal_sv}

    def save(self, images: Dict, save_dir: str, ext: str):
        try:
            os.makedirs(save_dir, exist_ok=True)
            for k, img in images.items():
                cv2.imwrite(os.path.join(save_dir, k + ext),
                            cv2.cvtColor(img, cv2.COLOR_RGB2BGR))
        except:
            print("saving error")
