from rest_framework import serializers
from reg_shop.models import Shops


class Shopsserializer(serializers.ModelSerializer):
    class Meta:
        model = Shops
        fields = '__all__'
