from rest_framework import serializers
from a_assigned_work.models import AssignedWork
class AssignedWorkserializer(serializers.ModelSerializer):

         class Meta:
             model=AssignedWork
             fields='__all__'
