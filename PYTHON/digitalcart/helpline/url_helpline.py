from django.conf.urls import url
from helpline import views

urlpatterns=[
    url('^$',views.post_helpline,name="post_helpline"),
    url(r'vsh/', views.Helplineview.as_view()),

]