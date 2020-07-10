from django.conf.urls import url
from track import views

urlpatterns=[

    url(r'vsh/',views.TrackView.as_view()),
    url(r'vshup/',views.TrackUp.as_view()),
]