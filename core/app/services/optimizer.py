import traceback
import os

import multiprocessing as mp
import requests
import numpy as np
import time
import cv2

from services.low_light_enhancer import LowLightEnhancer

from dto import OptimizeRequest

from utils.histogram import dynamic_hist_equal, hist_equal_ycrcb, hist_stretch_ycrcb, hist_equal_hsv
from utils.contrast import Ying_2017_CAIP
from utils.utils import get_logger
logger = get_logger(__name__, 'data/logs/core/optimization')


def dynamic_work(queue: mp.Queue):
    while True:
        time.sleep(1)
        if not queue.empty():
            try:
                data: OptimizeRequest = queue.get()
                logger.info(f"Dynamic worker got {id(data)}")
                output = dynamic_hist_equal(data.image)
                cv2.imwrite(os.path.join(data.save_dir, f"dynamic{data.ext}"), cv2.cvtColor(output, cv2.COLOR_RGB2BGR))
                response = requests.get(f"https://j7b301.p.ssafy.io/api/image/request-process?requestId={data.request_id}&finished=1")

                logger.info(f"Dynamic Worker {id(data)} Got Response {response.text}")
                logger.info(f"Dynamic Worker {id(data)} Complete")
            except Exception as e:
                logger.error(f"Dynamic Worker Failed !!! - {id(data)} - {traceback.format_exc()}")
                raise e


def exposure_work(queue: mp.Queue):
    while True:
        time.sleep(1)
        if not queue.empty():
            try:
                data: OptimizeRequest = queue.get()
                logger.info(f"Exposure worker got {id(data)}")
                output = Ying_2017_CAIP(data.image)
                cv2.imwrite(os.path.join(data.save_dir, f"exposure{data.ext}"), cv2.cvtColor(output, cv2.COLOR_RGB2BGR))
                response = requests.get(f"https://j7b301.p.ssafy.io/api/image/request-process?requestId={data.request_id}&finished=1")

                logger.info(f"Exposure Worker {id(data)} Got Response {response.text}")
                logger.info(f"Exposure Worker {id(data)} Complete")
            except Exception as e:
                logger.error(f"Exposure Worker Failed !!! - {id(data)} - {e}")
                raise e


class Optimizer():
    def __init__(self, low_light_threshold: int=42) -> None:
        try:
            self._low_enhancer = LowLightEnhancer()
            self._threshold = low_light_threshold
            
            self._dynamic_queue = mp.Queue()
            self._exposure_queue = mp.Queue()
            self._dynamic = mp.Process(target=dynamic_work, args=(self._dynamic_queue, ), daemon=True)
            self._exposure = mp.Process(target=exposure_work, args=(self._exposure_queue, ), daemon=True)

            self._dynamic.start()
            logger.info("Dynamic Enhancer Successfully Build")
            self._exposure.start()
            logger.info("Exposure Enhancer Successfully Build")
            logger.info("Optimizer Successfully Build")
        except Exception as e:
            logger.error(f"Building Optimizer Failed !!! - {e} : {traceback.format_exc()}")
            raise e
            

    def _get_brightness(self, image: np.ndarray) -> int:
        hsv = cv2.cvtColor(np.array(image), cv2.COLOR_RGB2HSV)
        return hsv[..., 2].mean()

    def _preprocess(self):
        return

    def _postprocess(self):
        return

    def process(self, optimize_request: OptimizeRequest) -> None:
        try:
            logger.info(f"Preprocessing {id(optimize_request)} ...")
            optimize_request.image = np.array(optimize_request.image)
            brightness = self._get_brightness(optimize_request.image)
            logger.info(f"Brightness {id(optimize_request)} is {brightness}")
            if brightness < self._threshold:
                logger.info(f"Enhance Brightness {id(optimize_request)} ...")
                optimize_request.image = self._low_enhancer.process(optimize_request.image)
                optimize_request.image = cv2.normalize(optimize_request.image, None, 0, 255,
                                    cv2.NORM_MINMAX).astype(np.uint8)
                logger.info(f"Enhance Brightness {id(optimize_request)} Complete")

            logger.info(f"Histogram Processing {id(optimize_request)} ...")
            ycrcb_equal = hist_equal_ycrcb(optimize_request.image)
            cv2.imwrite(os.path.join(optimize_request.save_dir, f"ycrcb_equal{optimize_request.ext}"), cv2.cvtColor(ycrcb_equal, cv2.COLOR_RGB2BGR))
            ycrcb_stretch = hist_stretch_ycrcb(optimize_request.image)
            cv2.imwrite(os.path.join(optimize_request.save_dir, f"ycrcb_stretch{optimize_request.ext}"), cv2.cvtColor(ycrcb_stretch, cv2.COLOR_RGB2BGR))
            hsv_equal_s = hist_equal_hsv(optimize_request.image, on="s")
            cv2.imwrite(os.path.join(optimize_request.save_dir, f"hsv_equal_s{optimize_request.ext}"), cv2.cvtColor(hsv_equal_s, cv2.COLOR_RGB2BGR))
            hsv_equal_v = hist_equal_hsv(optimize_request.image, on="v")
            cv2.imwrite(os.path.join(optimize_request.save_dir, f"hsv_equal_v{optimize_request.ext}"), cv2.cvtColor(hsv_equal_v, cv2.COLOR_RGB2BGR))
            hsv_equal_sv = hist_equal_hsv(optimize_request.image, on="sv")
            cv2.imwrite(os.path.join(optimize_request.save_dir, f"hsv_equal_sv{optimize_request.ext}"), cv2.cvtColor(hsv_equal_sv, cv2.COLOR_RGB2BGR))
            response = requests.get(f"https://j7b301.p.ssafy.io/api/image/request-process?requestId={optimize_request.request_id}&finished=5")
            
            logger.info(f"Histogram Processing {id(optimize_request)} Got Response {response.text}")
            logger.info(f"Histogram Processing {id(optimize_request)} Complete")

            logger.info(f"Put Queue {id(optimize_request)} ...")
            self._dynamic_queue.put(optimize_request)
            self._exposure_queue.put(optimize_request)
            logger.info(f"Put Queue {id(optimize_request)} Complete")

            return
        except Exception as e:
            logger.error(f"Enhancing Failed !!! - {id(optimize_request)} - {traceback.format_exc()}")
            raise e
