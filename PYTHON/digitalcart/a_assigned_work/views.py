from django.shortcuts import render
from django.http import HttpResponse

from rest_framework.views import APIView
from a_assigned_work.serializer import AssignedWorkserializer
from rest_framework.response import Response
from a_assigned_work.models import AssignedWork

class AssignedWorkview(APIView):
    def get(self,request):
        s=AssignedWork.objects.all()
        ser=AssignedWorkserializer(s,many=True)
        return Response(ser.data)

    # def post(self,request):
    #     obj = AssignedWork()
    #     obj.d_id = request.data["d_id"]
    #     obj.work = request.data["work"]
    #     obj.status = request.data["status"]
    #     obj.save()
    #     return HttpResponse("ok")

class UpdateStatus(APIView):
    def get(self,request):
        s=AssignedWork.objects.all()
        ser=AssignedWorkserializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = AssignedWork.objects.get(aid=request.data["aid"])

        obj.status = request.data["status"]
        obj.save()

        return HttpResponse("ok")
