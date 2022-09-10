from django.db import models
import mongoengine
from mongoengine import Document, fields
# Create your models here.

# mongoengine.connect(db="S07P22B301", host="ssafy.ngivl.mongodb.net", username="S07P22B301", password="zFoNN24jV5")

class Users(Document):
    _id = fields.IntField()
    nickname = fields.StringField(max_length=100)


class Test(Document):
    _id = fields.IntField()
    content = fields.StringField(max_length=100)
