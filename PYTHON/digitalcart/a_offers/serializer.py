from rest_framework import serializers
from a_offers.models import Offers


class Offersserializer(serializers.ModelSerializer):
    class Meta:
        model = Offers
        fields = '__all__'