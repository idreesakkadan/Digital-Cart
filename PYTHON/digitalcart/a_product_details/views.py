from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.views import APIView
from a_product_details.serializer import ProductDetailsserializer
from rest_framework.response import Response
from a_product_details.models import ProductDetails
# Create your views here.


class ProductDetailsview(APIView):
    def get(self,request):
        s=ProductDetails.objects.all()
        ser=ProductDetailsserializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = ProductDetails()
        obj.secid = request.data["secid"]
        obj.product_name = request.data["product_name"]
        obj.price = request.data["price"]
        obj.mfd_date = request.data["mfd_date"]
        obj.exp_date = request.data["exp_date"]
        obj.save()

        return HttpResponse("ok")



