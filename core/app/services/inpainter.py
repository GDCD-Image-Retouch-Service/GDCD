import numpy as np
from PIL.Image import Image

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
    def __init__(self) -> None:
        try:
            logger.info("Inpainter Successfully Build")
        except Exception as e:
            logger.error(f"Building Inpainter Failed !!! - {e}")

    def process(self, image: Image) -> np.ndarray:
        try:
            logger.info(f"Preprocessing {id(image)} ...")



            logger.info(f"Postprocessing {id(image)} Complete")
            arr = np.array(image)
            return np.zeros(arr.shape, dtype=np.uint8)
        except Exception as e:
            logger.error(f"Inpainting Failed !!! - {id(image)} - {traceback.format_exc()}")