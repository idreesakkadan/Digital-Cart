from django.conf.urls import url
from a_offers import views

urlpatterns=[

    url(r'vsh/', views.Offersview.as_view()),

]