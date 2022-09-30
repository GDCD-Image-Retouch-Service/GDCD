from dataclasses import dataclass

import numpy as np

@dataclass
class OptimizeRequest:
    img: np.ndarray
    user_id: int
    request_id: int
    save_dir: str
    ext: str
