from django.conf.urls import url
from reg_manage_d_boy import views

urlpatterns=[
    url('^$',views.show,name="show"),
    url('^reg/',views.reg_db,name="reg_db"),
    url('^do/(?P<cid>\w+)',views.add,name="add"),
    url('^del/(?P<cid>\w+)',views.delete,name="delete"),
    url(r'vsh/',views.Deliveryboyview.as_view()),


]