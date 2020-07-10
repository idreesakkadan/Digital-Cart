from django.conf.urls import url
from a_section import views

urlpatterns=[

    url(r'vsh/', views.Sctionview.as_view()),

]