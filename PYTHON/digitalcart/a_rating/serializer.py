from rest_framework import serializers
from a_rating.models import Rating


class Ratingserializer(serializers.ModelSerializer):
    class Meta:
        model = Rating
        fields = '__all__'