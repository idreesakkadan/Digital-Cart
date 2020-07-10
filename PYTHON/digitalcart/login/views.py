from django.shortcuts import render
from django.http import HttpResponse
from login.models import Login

# Create your views here.

from login.serializer import Loginserializer
from rest_framework.response import Response
from rest_framework.views import APIView

def hllo(request):
    return HttpResponse("hello")

def go(request):
    if request.method == "POST":
        un=request.POST.get("user")
        ps=request.POST.get("pass")
        obj = Login.objects.filter(username=un,password=ps)
        tp=''
        for o in obj:
            tp=o.type

        if tp=='admin':
            request.session['login']=o.uid
            return render(request, 'login/adminhome.html')
            #return HttpResponse("ok")
        elif tp == 'shop':
            request.session['login'] = o.uid
            return render(request, 'login/shophome.html')
            # return HttpResponse("ok")
        else:
            #return HttpResponse("ERROR")
            return render(request, 'login/login.html')



    #return HttpResponse("This is a new fumction")
    return render(request,'login/login.html')

class Loginview(APIView):
    def get(self,request):
        s=Login.objects.all()
        ser=Loginserializer(s,many=True)
        return Response(ser.data)
    def post(self,request):
        #ser=viewloginserializer(data=request.data)
        s = Login.objects.filter(username=request.data["username"],password=request.data["password"])
        ser = Loginserializer(s, many=True)
        return Response(ser.data)