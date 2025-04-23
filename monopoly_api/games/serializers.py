from .models import GameResult,PlayerData
from rest_framework import serializers

class PlayerDataSerializer(serializers.ModelSerializer):
     class Meta:
        model=PlayerData
        fields=['name','money','strategy',]
     def __str__(self):
        return f"{self.name} (${self.money}) - {self.strategy}"


class GameResultSerializer(serializers.ModelSerializer):
    players=PlayerDataSerializer(many=True,read_only=True)
    class Meta:
        model=GameResult
        fields = ['id', 'winner', 'strategy', 'turns', 'players']  # defines what shows in the API
    def __str__(self):
        return f"{self.winner} ({self.strategy}) - {self.turns} turns"

    
