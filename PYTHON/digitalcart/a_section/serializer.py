from rest_framework import serializers
from a_section.models import Section


class Sectionserializer(serializers.ModelSerializer):
    class Meta:
        model = Section
        fields = '__all__'