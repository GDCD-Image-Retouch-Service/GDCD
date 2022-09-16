from pydantic import BaseModel
from typing import List, Dict


class Scores(BaseModel):
    scores: List[Dict[str, float]]
