from fastapi import FastAPI, UploadFile, File
import uvicorn

from PIL import Image
import io

from typing import List

from services.nima import Nima


app = FastAPI()
nima = Nima(aes_path="./models/InceptionV3_aesthetic.pt",
            tec_path="./models/InceptionV3_technical.pt")


@app.post("/score-image")
def get_score(images: List[UploadFile] = File(...)):
    inputs = [Image.open(io.BytesIO(img.file.read())) for img in images]
    results = nima.predict(inputs)
    return results
