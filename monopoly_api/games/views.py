from rest_framework import generics
from .models import GameResult
from .serializers import GameResultSerializer

class GameResultListView(generics.ListAPIView):
    queryset=GameResult.objects.all()
    serializer_class=GameResultSerializer
    # permission_classes=
    
    
class GameResultDetailView(generics.RetrieveAPIView):
    queryset = GameResult.objects.all()
    serializer_class = GameResultSerializer
    
    
class GameResultWinnerDetailView(generics.ListAPIView):
    # queryset = GameResult.objects.all()
    serializer_class = GameResultSerializer
    # lookup_field='winner'
    
    def get_queryset(self):
        winner_name = self.kwargs['winner']
        return GameResult.objects.filter(winner=winner_name)
    
    
class GameResultByStrategyView(generics.ListAPIView):
    serializer_class=GameResultSerializer
    
    def get_queryset(self):
        return GameResult.objects.filter(strategy=self.kwargs['strategy'])