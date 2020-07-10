from django.conf.urls import url
from a_payment import views

urlpatterns=[

    url(r'vsh/', views.Paymentview.as_view()),

]