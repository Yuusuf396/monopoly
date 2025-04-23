from django.urls import path
from .views import GameResultDetailView,GameResultListView,GameResultWinnerDetailView,GameResultByStrategyView

urlpatterns = [
    path('gameresultlist/', GameResultListView.as_view(), name='game-list'),
    path('gameresultdetail/<int:pk>/', GameResultDetailView.as_view(), name='game-detail'),
    path('gameresults/name/<str:winner>/', GameResultWinnerDetailView.as_view(), name='game-detail-winner'),
    path('gameresults/strategy/<str:strategy>/', GameResultByStrategyView.as_view(), name='game-detail-winner'),
    
    
]
