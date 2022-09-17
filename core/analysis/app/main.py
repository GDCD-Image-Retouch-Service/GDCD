from fastapi import FastAPI, UploadFile, File
import uvicorn

from PIL import Image
import io

from typing import List, Dict

from services.nima import Nima

app = FastAPI()
nima = Nima(aes_path="./models/InceptionV3_aesthetic.pt",
            tec_path="./models/InceptionV3_technical.pt")


@app.post("/score-image", response_model=List[Dict[str, float]])
def get_score(images: List[UploadFile] = File(...)):
    print(images)
    inputs = [Image.open(io.BytesIO(img.file.read())) for img in images]
    results = nima.predict(inputs)
    return results
