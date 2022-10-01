from fastapi import FastAPI, UploadFile, File, Form, Request

from PIL import Image
import io
import os
import numpy as np

from typing import List, Dict

from services.nima import Nima
from services.yolo import Yolo
from services.enhancer import Optimizer

from dto import OptimizeRequest

import traceback
import logging
logger = logging.getLogger(__name__)
logger.setLevel(logging.INFO)
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
stream_handler = logging.StreamHandler()
stream_handler.setFormatter(formatter)
logger.addHandler(stream_handler)
file_handler = logging.FileHandler('data/logs/core/server/main.log')
file_handler.setFormatter(formatter)
logger.addHandler(file_handler)

try:
    app = FastAPI()
    nima = Nima(aes_path="models/aesthetic_InceptionV3_0725.pt",
                tec_path="models/technical_InceptionV3_0841.pt")
    yolo = Yolo(det_path="models/yolov7-d6.pt")
    enhancer = Optimizer(low_light_threshold=42)
except Exception as e:
    logger.error(f"Core Server Failed!!! - {e}")
    exit()


@app.post("/score-image", response_model=List[Dict[str, float]])
def get_score(request: Request, images: List[UploadFile] = File(...)):
    try:
        logger.info(f"Request Image Scoring from {request.client.host}:{request.client.port}")
        inputs = [Image.open(io.BytesIO(img.file.read())).convert("RGB") for img in images]
        results = nima.predict(inputs)
        logger.info(f"Response Image Scoring to {request.client.host}:{request.client.port}")
        return results
    except Exception as e:
        logger.error(f"Image Scoring failed from {request.client.host}:{request.client.port} - {traceback.format_exc()}")

@app.post("/detect-object")
def get_detect(request: Request, image: UploadFile = File(...)):
    try:
        logger.info(f"Request Object Detection from {request.client.host}:{request.client.port}")
        inputs = Image.open(io.BytesIO(image.file.read())).convert("RGB")
        results = yolo.predict(inputs)
        logger.info(f"Response Object Detection to {request.client.host}:{request.client.port}")
        return results
    except Exception as e:
        logger.error(f"Image Scoring failed from {request.client.host}:{request.client.port} - {traceback.format_exc()}")

@app.post("/optimize-request")
def get_optimize_request(request: Request, image: UploadFile = File(...), user_id: int = Form(), request_id: int = Form()):
    try:
        logger.info(f"Request Image Optimization from {request.client.host}:{request.client.port}")
        ext = os.path.splitext(image.filename)[1]
        save_dir = os.path.join("data", "buffer", str(user_id))
        os.makedirs(save_dir, exist_ok=True)
        img: np.ndarray = np.array(Image.open(io.BytesIO(image.file.read())).convert("RGB"))
        
        enhancer.process(OptimizeRequest(img=img, user_id=user_id, request_id=request_id, save_dir=save_dir, ext=ext))
        logger.info(f"Response Image Optimization to {request.client.host}:{request.client.port}")

        return request_id
    except Exception as e:
        logger.error(f"Image Optimization failed from {request.client.host}:{request.client.port} - {traceback.format_exc()}")


# @app.post("/optimize-image", response_model=List[Dict[str, float]])
# def get_score(image: UploadFile, user_id: str):
#     input_image = Image.open(io.BytesIO(image.file.read()))
#     enhancer.process(input_image)
#     res, im_png = cv2.imencode(".png", cv2.cvtColor(result, cv2.COLOR_RGB2BGR))
#     return StreamingResponse(io.BytesIO(im_png.tobytes()), media_type="image/png")
