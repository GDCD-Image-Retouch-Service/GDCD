FROM python:3.8.13

WORKDIR /app

COPY requirements.txt .

RUN apt-get update && apt-get upgrade -y
RUN apt-get install libgl1-mesa-glx -y

RUN pip3 install torch==1.12.1 torchvision==0.13.1 torchaudio==0.12.1 --extra-index-url https://download.pytorch.org/whl/cpu
RUN pip install --no-cache-dir --upgrade -r requirements.txt

COPY app .

# CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "80"]


