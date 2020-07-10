from django.conf.urls import url
from a_register import views

urlpatterns=[

    url(r'vsh/', views.Registerview.as_view()),

]