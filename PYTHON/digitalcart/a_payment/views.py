from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.views import APIView
from a_payment.serializer import Paymentserializer
from rest_framework.response import Response
from a_payment.models import Payment
import datetime
# Create your views here.


class Paymentview(APIView):
    def get(self,request):
        s=Payment.objects.all()
        ser=Paymentserializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = Payment()
        obj.pid = request.data["pid"]
        obj.mode = request.data["mode"]
        obj.amount = request.data["amount"]
        obj.date = datetime.date.today()
        obj.time = datetime.datetime.now()
        obj.save()

        return HttpResponse("ok")
