# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class ProductDetails(models.Model):
    productid = models.AutoField(primary_key=True)
    secid = models.IntegerField()
    product_name = models.CharField(db_column='product name', max_length=50)  # Field renamed to remove unsuitable characters.
    price = models.FloatField()
    mfd_date = models.DateField(db_column='mfd date')  # Field renamed to remove unsuitable characters.
    exp_date = models.DateField(db_column='exp date')  # Field renamed to remove unsuitable characters.

    class Meta:
        managed = False
        db_table = 'product_details'
