from django.conf.urls import url
from a_rating import views

urlpatterns=[

    url(r'vsh/', views.Ratingview.as_view()),

]