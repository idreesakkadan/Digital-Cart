from rest_framework import serializers
from a_payment.models import Payment


class Paymentserializer(serializers.ModelSerializer):
    class Meta:
        model = Payment
        fields = '__all__'