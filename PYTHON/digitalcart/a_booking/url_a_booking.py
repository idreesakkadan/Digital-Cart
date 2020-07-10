from django.conf.urls import url
from a_booking import views

urlpatterns=[

    url(r'vsh/', views.Bookingview.as_view()),

]