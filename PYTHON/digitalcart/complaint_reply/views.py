from django.shortcuts import render
from django.http import HttpResponse
from complaint_reply.models import Complaint
# Create your views here.

from complaint_reply.serializer import Complaintserializer
from rest_framework.response import Response
from rest_framework.views import APIView
import datetime

def show(request):
    objlist=Complaint.objects.all()
    context={
        'objval':objlist,
    }
    return render(request, 'complaint_reply/complaint.html',context)

def complaint(request,cid):
    request.session['rp']=cid
    return post_cmp(request)

def post_cmp(request):
    if request.method=="POST":
        tp=request.session['rp']
        obj= Complaint.objects.get(id=tp)
        obj.reply = request.POST.get("reply")
        obj.date=datetime.date.today()
        obj.save()
        return show(request)
    else:
        return render(request, 'complaint_reply/view complaint and post reply.html')
'''

obj.uid = request.POST.get("type")
obj.date = request.POST.get("date")

'''
class Complaintview(APIView):
    def get(self,request):
        s=Complaint.objects.all()
        ser=Complaintserializer(s,many=True)
        return Response(ser.data)

    def post(self,request):
        obj = Complaint()
        obj.uid = request.data["uid"]
        obj.complaint = request.data["complaint"]
        obj.date = datetime.date.today()
        obj.reply = 'pending'
        obj.save()
        return HttpResponse("ok")
