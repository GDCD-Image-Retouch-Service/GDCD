from fastapi import FastAPI, UploadFile, File, Form
from starlette.responses import StreamingResponse

from PIL import Image
import io
import os

from typing import List, Dict

from services.nima import Nima
from services.enhancer import Enhancer

data_path = os.path.join("app", "data")
os.makedirs("data/logs/core/gunicorn", exist_ok=True)
os.makedirs("data/logs/core/scoring", exist_ok=True)
os.makedirs("data/logs/core/optimize", exist_ok=True)
os.makedirs("data/logs/core/csv_save", exist_ok=True)

app = FastAPI()
nima = Nima(aes_path="models/aesthetic_InceptionV3_0725.pt",
            tec_path="models/technical_InceptionV3_0841.pt")
enhancer = Enhancer(low_light_threshold=42)


@app.post("/score-image", response_model=List[Dict[str, float]])
def get_score(images: List[UploadFile] = File(...)):
    inputs = [Image.open(io.BytesIO(img.file.read())) for img in images]
    results = nima.predict(inputs)
    return results


@app.post("/optimize-image", response_model=List[Dict[str, Dict[str, float]]])
def get_score(image: UploadFile = File(...), user_id: str = Form()):
    filename, ext = os.path.splitext(image.filename)

    inputs: Image = Image.open(io.BytesIO(image.file.read()))
    save_dir = os.path.join(data_path, "buffer", user_id)

    outputs: dict = enhancer.process(inputs)
    enhancer.save(outputs, save_dir, ext)

    keys = list(outputs.keys())
    values = list(outputs.values())
    scores = nima.predict([Image.fromarray(v) for v in values])

    return [{os.path.join(save_dir, k + ext): s} for k, s in zip(keys, scores)]
# @app.post("/optimize-image", response_model=List[Dict[str, float]])
# def get_score(image: UploadFile, user_id: str):
#     input_image = Image.open(io.BytesIO(image.file.read()))
#     enhancer.process(input_image)
#     res, im_png = cv2.imencode(".png", cv2.cvtColor(result, cv2.COLOR_RGB2BGR))
#     return StreamingResponse(io.BytesIO(im_png.tobytes()), media_type="image/png")
