from django.conf.urls import url
from a_assigned_work import views

urlpatterns=[

    url(r'vsh/', views.AssignedWorkview.as_view()),
    url(r'upvsh/', views.UpdateStatus.as_view()),
]







