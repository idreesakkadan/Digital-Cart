from django.conf.urls import url
from a_product_details import views

urlpatterns=[

    url(r'vsh/', views.ProductDetailsview.as_view()),

]