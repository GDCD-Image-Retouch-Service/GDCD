import sys
import os
sys.path.append(os.path.dirname(os.path.abspath(os.path.dirname(__file__))))

import torch
import torchvision.transforms as transforms
import numpy as np
from numpy import random

from PIL.Image import Image
from typing import List, Dict

from utils.plots import plot_one_box
from utils.torch_utils import select_device, load_classifier, time_synchronized, TracedModel
from utils.general import check_img_size, check_requirements, check_imshow, non_max_suppression, apply_classifier, \
    scale_coords, xyxy2xywh, strip_optimizer, set_logging, increment_path
from utils.datasets import letterbox
from models.experimental import attempt_load


class Yolo():
    def __init__(self, det_path: str) -> None:
        self.device: str = "cuda" if torch.cuda.is_available() else "cpu"
        self.det_module = attempt_load(det_path, map_location=self.device)
        self.names = self.det_module.module.names if hasattr(self.det_module, 'module') else self.det_module.names
        self.stride = int(self.det_module.stride.max())  # model stride
    

    def predict(self, image: Image) -> Dict[str, float]:
        img0 = np.array(image)
        imgsz = check_img_size(min(img0.shape[:2]), s=self.stride)  # check img_size
        print(imgsz, min(img0.shape[:2]))
        img = letterbox(img0, imgsz, stride=self.stride)[0]
        img = img.transpose(2, 0, 1)
        img = np.ascontiguousarray(img)
        img = torch.from_numpy(img).to(self.device).float()
        img /= 255.0  # 0 - 255 to 0.0 - 1.0
        img = img.unsqueeze(0)
        colors = [[random.randint(0, 255) for _ in range(3)] for _ in self.names]

        with torch.no_grad():
            outputs = self.det_module(img, augment='store_true')[0]
            outputs = non_max_suppression(
            outputs, 0.25, 0.45, agnostic='store_true')[0]
        gn = torch.tensor(img0.shape)[[1, 0, 1, 0]]
        outputs[:, :4] = scale_coords(
                    img.shape[2:], outputs[:, :4], img0.shape).round()
        line = []
        idx = 0
        # Write results
        for *xyxy, conf, cls in reversed(outputs):
            # if save_txt:  # Write to file
            xywh = (xyxy2xywh(torch.tensor(xyxy).view(1, 4)
                                ) / gn).view(-1).tolist()  # normalized xywh
            # label format
            line.append({})
            line[idx]['class']=str(self.names[int(cls)])
            label = f'{self.names[int(cls)]} {conf:.2f}'
            c1, c2 = plot_one_box(xyxy, img0, label=label,
                            color=colors[int(cls)], line_thickness=1)
            line[idx]['ul']=c1
            line[idx]['dr']=c2
            idx += 1
        return line

