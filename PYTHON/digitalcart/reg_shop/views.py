from django.shortcuts import render
from django.http import HttpResponse
from reg_shop.models import Shops

# Create your views here.
from reg_shop.serializer import Shopsserializer
from rest_framework.response import Response
from rest_framework.views import APIView
from login.models import Login


def view(request):
    objlist=Shops.objects.all()
    context={
        'objval':objlist,
    }
    return render(request,'reg_shop/manage_shops.html',context)

def update(request,sid):
    request.session['rp']=sid
    return regshops(request)

def regshops(request):
    tp = request.session['rp']
    objlist = Shops.objects.get(sid=tp)
    context = {
        'objval': objlist,
    }
    if request.method=="POST":
         obj= Shops.objects.get(sid=tp)
         obj.name = request.POST.get("name")
         obj.address = request.POST.get("address")
         obj.ph = request.POST.get("contact")
         obj.email = request.POST.get("email")
         obj.save()
         return view(request)
    else:
         return render(request, 'reg_shop/manage.html', context)

def reg(request):

    if request.method=="POST":
         obj= Shops()
         obj.name = request.POST.get("name")
         obj.address = request.POST.get("address")
         obj.ph = request.POST.get("contact")
         obj.email = request.POST.get("email")
         obj.save()
         return view(request)

    return render(request, 'reg_shop/register manage shops.html')



def delete(request,sid):
         obj= Shops.objects.get(sid=sid)

         obj.delete()
         return view(request)


class Shopview(APIView):
    def get(self,request):
        s=Shops.objects.all()
        ser=Shopsserializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = Shops()
        ob = Login()
        obj.name = request.data["name"]
        obj.address = request.data["address"]
        obj.ph = request.data["ph"]
        obj.email = request.data["email"]
        obj.save()

        sid = Shops.objects.all().aggregate(Max('sid'))
        sidd = list(sid.values())[0]

        ob.uid = sidd + 1
        ob.username = request.data["email"]
        ob.password = request.data["ph"]
        ob.type = 'shop'
        ob.save()

        return HttpResponse("ok")





