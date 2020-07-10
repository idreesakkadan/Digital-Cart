from rest_framework import serializers
from helpline.models import Helpline


class Helplineserializer(serializers.ModelSerializer):
    class Meta:
        model = Helpline
        fields = '__all__'