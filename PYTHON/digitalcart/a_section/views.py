from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.views import APIView
from a_section.serializer import Sectionserializer
from rest_framework.response import Response
from a_section.models import Section
# Create your views here.


class Sctionview(APIView):
    def get(self,request):
        s=Section.objects.all()
        ser=Sectionserializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = Section()
        obj.shopid = request.data["shopid"]
        obj.sections = request.data["sections"]
        obj.save()
        return HttpResponse("ok")

