from django.conf.urls import url
from reg_shop import views

urlpatterns=[
    url('^$',views.view,name="view"),
    url('^reg/',views.reg,name="reg"),
    url('^update/(?P<sid>\w+)',views.update,name="update"),
    url('^delete/(?P<sid>\w+)',views.delete,name="delete"),
    url(r'vsh/',views.Shopview.as_view()),


]