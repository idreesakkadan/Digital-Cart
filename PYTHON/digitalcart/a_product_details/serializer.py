from rest_framework import serializers
from a_product_details.models import ProductDetails


class ProductDetailsserializer(serializers.ModelSerializer):
    class Meta:
        model = ProductDetails
        fields = '__all__'