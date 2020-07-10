from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.views import APIView
from track.serializer import TrackSerializer
from rest_framework.response import Response
from track.models import Track
# Create your views here.


class TrackView(APIView):
    def get(self,request):
        s=Track.objects.all()
        ser=TrackSerializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = Track()
        obj.dbid = request.data["dbid"]
        obj.lat = request.data["lat"]
        obj.lon = request.data["lon"]
        obj.save()
        return HttpResponse("ok")

class TrackUp(APIView):
    def get(self,request):
        s=Track.objects.all()
        ser=TrackSerializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = Track.objects.get(dbid=request.data["dbid"])
        obj.lat = request.data["lat"]
        obj.lon = request.data["lon"]
        obj.save()
        return HttpResponse("ok")

