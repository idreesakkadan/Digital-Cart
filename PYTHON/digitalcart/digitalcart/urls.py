"""digitalcart URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from django.conf.urls import url,include
from django.contrib.staticfiles.urls import  staticfiles_urlpatterns

urlpatterns = [
    url(r'^login/',include('login.url_login')),
    url(r'^complaint_reply/',include('complaint_reply.url_complaint')),
    url(r'^helpline/',include('helpline.url_helpline')),
    url(r'^reg_manage_d_boy/',include('reg_manage_d_boy.url_reg')),
    url(r'^reg_shop/', include('reg_shop.url_reg_shop')),
    url(r'^a_assigned_work/', include('a_assigned_work.url_a_assigned_work')),
    url(r'^a_booking/', include('a_booking.url_a_booking')),
    url(r'^a_offers/', include('a_offers.url_a_offers')),
    url(r'^a_payment/', include('a_payment.url_a_payment')),
    url(r'^a_product_details/', include('a_product_details.url_a_product_details')),
    url(r'^a_rating/', include('a_rating.url_a_rating')),
    url(r'^a_review/', include('a_review.url_a_review')),
    url(r'^a_section/', include('a_section.url_a_section')),
    url(r'^a_register/', include('a_register.url_a_register')),


]
urlpatterns+=staticfiles_urlpatterns()