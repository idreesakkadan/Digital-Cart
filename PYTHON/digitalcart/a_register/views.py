from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.views import APIView
from a_register.serializer import Registerserializer
from rest_framework.response import Response
from a_register.models import Register
from login.models import Login
# Create your views here.



class Registerview(APIView):
    def get(self,request):
        s=Register.objects.all()
        ser=Registerserializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = Register()

        ob = Login()

        obj.first_name = request.data["first_name"]
        obj.second_name = request.data["second_name"]
        obj.number = request.data["number"]
        obj.address = request.data["address"]
        obj.email = request.data["email"]
        obj.password = request.data["password"]
        obj.save()

        sid = Register.objects.all().aggregate(Max('id'))
        sidd = list(sid.values())[0]

        ob.uid = sidd + 1
        ob.username = request.data["email"]
        ob.password = request.data["password"]
        ob.type = 'user'
        ob.save()

        return HttpResponse("ok")