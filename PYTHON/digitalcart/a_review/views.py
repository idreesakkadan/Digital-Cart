from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.views import APIView
from a_review.serializer import Reviewserializer
from rest_framework.response import Response
from a_review.models import Review
# Create your views here.


class Reviewview(APIView):
    def get(self,request):
        s=Review.objects.all()
        ser=Reviewserializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = Review()
        obj.date = request.data["date"]
        obj.review = request.data["review"]
        obj.save()

        return HttpResponse("ok")



