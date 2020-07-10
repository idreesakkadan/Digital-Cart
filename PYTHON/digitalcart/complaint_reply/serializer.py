from rest_framework import serializers
from complaint_reply.models import Complaint
class Complaintserializer(serializers.ModelSerializer):

         class Meta:
             model=Complaint
             fields='__all__'