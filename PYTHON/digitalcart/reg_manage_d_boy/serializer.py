from rest_framework import serializers
from reg_manage_d_boy.models import Deliveryboy
class Deliveryboyserializer(serializers.ModelSerializer):

         class Meta:
             model=Deliveryboy
             fields='__all__'
