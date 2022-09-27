import numpy as np
import io
import base64
from PIL import Image
import random


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
