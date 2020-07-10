from django.shortcuts import render
from django.http import HttpResponse
from reg_manage_d_boy.models import Deliveryboy

# Create your views here.
from reg_manage_d_boy.serializer import Deliveryboyserializer
from rest_framework.response import Response
from rest_framework.views import APIView
from django.db.models import Max
from login.models import Login


def show(request):

    objlist=Deliveryboy.objects.all()
    context={
        'objval':objlist,
    }
    return render(request,'reg_manage_d_boy/manage_d_boy.html',context)

def add(request,cid):
    request.session['rp']=cid
    return update_db(request)

def update_db(request):
    tp = request.session['rp']
    objlist = Deliveryboy.objects.get(id=tp)
    context = {
        'objval': objlist,
    }
    if request.method=="POST":
        obj = Deliveryboy.objects.get(id=tp)
        obj.name = request.POST.get("name")
        obj.number = request.POST.get("contact")
        obj.email = request.POST.get("email")
        obj.save()
        return show(request)
    else:
        return render(request, 'reg_manage_d_boy/register_manage_d_boy.html',context)

def reg_db(request):

    if request.method=="POST":
         obj= Deliveryboy()
         obj.name = request.POST.get("name")
         obj.number = request.POST.get("contact")
         obj.email = request.POST.get("email")
         obj.save()
         return show(request)

    return render(request, 'reg_manage_d_boy\managedb.html')


def delete(request, cid):
    obj = Deliveryboy.objects.get(id=cid)

    obj.delete()
    return show(request)

class Deliveryboyview(APIView):
    def get(self,request):
        s=Deliveryboy.objects.all()
        ser=Deliveryboyserializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = Deliveryboy()
        ob = Login()
        obj.name = request.data["name"]
        obj.number = request.data["number"]
        obj.email = request.data["email"]
        obj.save()

        sid = Deliveryboy.objects.all().aggregate(Max('id'))
        sidd = list(sid.values())[0]

        ob.uid = sidd + 1
        ob.username = request.data["email"]
        ob.password = request.data["number"]
        ob.type = 'deliveryboy'
        ob.save()

        return HttpResponse("ok")



