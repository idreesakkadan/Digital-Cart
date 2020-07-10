from django.conf.urls import url
from complaint_reply import views

urlpatterns=[
    url('^$',views.show,name="show"),
    url('^reply/(?P<cid>\w+)',views.complaint,name="complaint"),
    url(r'vsh/',views.Complaintview.as_view()),

]