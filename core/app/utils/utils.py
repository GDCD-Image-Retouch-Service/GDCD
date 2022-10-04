import os
import logging

import numpy as np
import base64
import io
from PIL import Image


def get_logger(name, save):
    logger = logging.getLogger(name)
    logger.setLevel(logging.INFO)
    formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
    stream_handler = logging.StreamHandler()
    stream_handler.setFormatter(formatter)
    logger.addHandler(stream_handler)
    os.makedirs(save, exist_ok=True)
    file_handler = logging.FileHandler(os.path.join(save, f"{name}.log"))
    file_handler.setFormatter(formatter)
    logger.addHandler(file_handler)
    return logger


def bytes_to_image(byte: str) -> Image.Image:
    return Image.open(io.BytesIO(byte))


def base64_to_image(code: str) -> Image.Image:
    return Image.open(io.BytesIO(base64.b64decode(code)))


def image_to_base64(image: Image) -> str:
    image_file = io.BytesIO()
    image.save(image_file, format="JPEG")
    image_bytes = image_file.getvalue()
    return base64.b64encode(image_bytes)


def normalize_labels(labels):
    labels_np = np.array(labels)
    return labels_np / labels_np.sum()


def calc_mean_score(score_dist):
    score_dist = normalize_labels(score_dist)
    return (score_dist*np.arange(1, 11)).sum()
