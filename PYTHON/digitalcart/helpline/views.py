from django.shortcuts import render
from helpline.models import Helpline
from rest_framework.views import APIView
from helpline.serializer import Helplineserializer
from rest_framework.response import Response
from django.http import HttpResponse

# Create your views here.

def post_helpline(request):

    if request.method=="POST":
        obj=Helpline()
        obj.type = request.POST.get("type")
        obj.number = request.POST.get("number")
        obj.save()
    return render(request, 'helpline/add helpline number.html')


class Helplineview(APIView):
    def get(self,request):
        s=Helpline.objects.all()
        ser=Helplineserializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = Helpline()
        obj.type = request.data["type"]
        obj.number = request.data["number"]
        obj.save()

        return HttpResponse("ok")
