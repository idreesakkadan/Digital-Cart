from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.views import APIView
from a_offers.serializer import Offersserializer
from rest_framework.response import Response
from a_offers.models import Offers
# Create your views here.


class Offersview(APIView):
    def get(self,request):
        s=Offers.objects.all()
        ser=Offersserializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = Offers()
        obj.section = request.data["section"]
        obj.offer = request.data["offer"]
        obj.pid = request.data["pid"]
        obj.save()

        return HttpResponse("ok")



