from rest_framework import serializers
from a_review.models import Review


class Reviewserializer(serializers.ModelSerializer):
    class Meta:
        model = Review
        fields = '__all__'