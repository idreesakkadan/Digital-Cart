from rest_framework import serializers
from a_booking.models import Booking
class Bookingserializer(serializers.ModelSerializer):

         class Meta:
             model=Booking
             fields='__all__'
