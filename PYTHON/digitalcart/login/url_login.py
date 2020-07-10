from django.conf.urls import url
from login import views

urlpatterns=[
    url('^go/',views.hllo,name="hllo"),
    url('^$',views.go,name="go"),
    url(r'vsh/',views.Loginview.as_view()),

]