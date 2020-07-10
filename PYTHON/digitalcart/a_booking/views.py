from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.views import APIView
from a_booking.serializer import Bookingserializer
from rest_framework.response import Response
from a_booking.models import Booking
import datetime
# Create your views here.


class Bookingview(APIView):
    def get(self,request):
        s=Booking.objects.all()
        ser=Bookingserializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = Booking()

        obj.pid = request.data["pid"]
        obj.uid = request.data["uid"]
        obj.date = datetime.datetime.today()
        obj.status = 'pending'
        obj.save()

        return HttpResponse("ok")




