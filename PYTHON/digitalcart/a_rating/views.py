from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.views import APIView
from a_rating.serializer import Ratingserializer
from rest_framework.response import Response
from a_rating.models import Rating
# Create your views here.


class Ratingview(APIView):
    def get(self,request):
        s=Rating.objects.all()
        ser=Ratingserializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = Rating()
        obj.uid=request.data["uid"]
        obj.d_id = request.data["d_id"]
        obj.deliveryboy_rating = request.data["deliveryboy_rating"]
        obj.comments = request.data["comments"]
        obj.save()

        return HttpResponse("ok")

