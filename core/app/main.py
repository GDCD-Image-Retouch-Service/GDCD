import traceback
import os

from fastapi import FastAPI, UploadFile, File, Form, Request

import numpy as np
import json
import cv2
import io
from PIL import Image

from typing import Dict

from services.nima import Nima
from services.yolo import Yolo
from services.optimizer import Optimizer
from services.inpainter import Inpainter

from dto import OptimizeRequest

from utils.utils import get_logger

logger = get_logger(__name__, 'data/logs/core/server')

try:
    app = FastAPI()
    nima = Nima(aes_path="models/saved/aesthetic_InceptionV3_0725.pt",
                tec_path="models/saved/technical_InceptionV3_0841.pt")
    yolo = Yolo(det_path="models/saved/yolov7-d6.pt")
    optimizer = Optimizer(low_light_threshold=42)
    inpainter = Inpainter(model_path="models/saved/best.ckpt")
except Exception as e:
    logger.error(f"Core Server Failed!!! - {e}")
    exit()

logger.info("Core Server Successfully Build")

@app.post("/score-image", response_model=Dict[str, float])
def get_score(request: Request, image: UploadFile = File(...)):
    try:
        logger.info(f"Request Image Scoring from {request.client.host}:{request.client.port}")

        results = nima.process([Image.open(io.BytesIO(image.file.read())).convert("RGB")])[0]

        logger.info(f"Response Image Scoring to {request.client.host}:{request.client.port}")
        return results
    except Exception as e:
        logger.error(f"Image Scoring failed from {request.client.host}:{request.client.port} - {e} : {traceback.format_exc()}")

@app.get("/score-image-by-user-id")
def get_score(request: Request, userId: str):
    try:
        logger.info(f"Request Image Scoring by Paths from {request.client.host}:{request.client.port}")

        files = os.listdir(f"data/buffer/{userId}")
        results = nima.process([Image.open(os.path.join("data/buffer", userId, path)).convert("RGB") for path in files])

        logger.info(f"Response Image Scoring by Paths to {request.client.host}:{request.client.port}")
        path_prefix = f"https://j7b301.p.ssafy.io/api/image?from=/app/data/buffer/{userId}/"
        return [{path_prefix + filename: result} for filename, result in zip(files, results)]
    except Exception as e:
        logger.error(f"Image Scoring by Paths failed from {request.client.host}:{request.client.port} - {e} : {traceback.format_exc()}")

@app.post("/detect-object")
def get_detect(request: Request, image: UploadFile = File(...)):
    try:
        logger.info(f"Request Object Detection from {request.client.host}:{request.client.port}")

        results = yolo.predict(Image.open(io.BytesIO(image.file.read())).convert("RGB"))

        logger.info(f"Response Object Detection to {request.client.host}:{request.client.port}")
        return results
    except Exception as e:
        logger.error(f"Image Scoring failed from {request.client.host}:{request.client.port} - {e} : {traceback.format_exc()}")

@app.post("/optimize-request")
def get_optimize_request(request: Request, image: UploadFile = File(...), userId: int = Form(), requestId: int = Form()):
    try:
        logger.info(f"Request Image Optimization from {request.client.host}:{request.client.port}")

        ext = os.path.splitext(image.filename)[1]
        save_dir = os.path.join("data", "buffer", str(userId))
        os.makedirs(save_dir, exist_ok=True)
        
        optimizer.process(OptimizeRequest(image=Image.open(io.BytesIO(image.file.read())).convert("RGB"), user_id=userId, request_id=requestId, save_dir=save_dir, ext=ext))
        
        logger.info(f"Response Image Optimization to {request.client.host}:{request.client.port}")
        return requestId
    except Exception as e:
        logger.error(f"Image Optimization failed from {request.client.host}:{request.client.port} - {e} : {traceback.format_exc()}")

@app.post("/inpaint-image")
def get_score(request: Request, image: UploadFile = File(), points = Form(), userId = Form()):
    try:
        logger.info(f"Request Image Inpainting from {request.client.host}:{request.client.port}")
        
        _, ext = os.path.splitext(image.filename)
        result: np.ndarray = inpainter.process(Image.open(io.BytesIO(image.file.read())).convert("RGB"), json.loads(points))
        os.makedirs(f"/app/data/buffer/{userId}", exist_ok=True)
        cv2.imwrite(f"/app/data/buffer/{userId}/inpainting{ext}", cv2.cvtColor(result, cv2.COLOR_RGB2BGR))

        logger.info(f"Response Image Inpainting to {request.client.host}:{request.client.port}")
        # _, im_png = cv2.imencode(ext, cv2.cvtColor(result, cv2.COLOR_RGB2BGR))
        # return StreamingResponse(io.BytesIO(im_png.tobytes()), media_type=f"image/png")
        return f"https://j7b301.p.ssafy.io/api/image?from=/app/data/buffer/{userId}/inpainting{ext}"
    except Exception as e:
        logger.error(f"Image Inpainting Failed from {request.client.host}:{request.client.port} - {e} : {traceback.format_exc()}")
