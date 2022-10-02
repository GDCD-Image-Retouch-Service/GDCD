from dataclasses import dataclass

from PIL.Image import Image

@dataclass
class OptimizeRequest:
    image: Image
    user_id: int
    request_id: int
    save_dir: str
    ext: str
