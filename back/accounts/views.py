from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework.decorators import api_view
from mongoengine import *
from .models import Users, Test


# id_num = 10001
# @api_view(['GET',])
# def hello(request):
    # global id_num
    # for user in Users.objects:
    #     print(user)
    # print("ㅋㅋ 망함")
    # user = Users(nickname='애리').save()
    # test = Test(_id=1234, content="이것도 되나?").save()
    # id_num += 1
    # id_num = 1234
    # print(user)
    # return HttpResponse("성공했나?")