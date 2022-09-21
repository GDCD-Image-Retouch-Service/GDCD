from fastapi import FastAPI, UploadFile, File
import uvicorn

from PIL import Image
import io

from typing import List, Dict

from services.nima import Nima

app = FastAPI(root_path="/core")
nima = Nima(aes_path="models/aesthetic_InceptionV3_0725.pt",
            tec_path="models/technical_ResNet152_0883.pt")

@app.post("/score-image", response_model=List[Dict[str, float]])
def get_score(images: List[UploadFile] = File(...)):
    inputs = [Image.open(io.BytesIO(img.file.read())) for img in images]
    results = nima.predict(inputs)
    return results
