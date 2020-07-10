from django.conf.urls import url
from a_review import views

urlpatterns=[

    url(r'vsh/', views.Reviewview.as_view()),

]