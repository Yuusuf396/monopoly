import os
import django
import json

# Setup Django environment
os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'monopoly_api.settings')
django.setup()

from games.models import GameResult, PlayerData

# Load JSON
with open('new_all_results.json', 'r') as f:
    data = json.load(f)

# Optional: clear old data before loading new (optional, but useful)
GameResult.objects.all().delete()
PlayerData.objects.all().delete()

# Load into DB
for entry in data:
    game = GameResult.objects.create(
        winner=entry['winner'],
        strategy=entry['strategy'],
        turns=entry['turns']
    )

    for player in entry['players']:
        PlayerData.objects.create(
            game=game,
            name=player['name'],
            money=player['money'],
            strategy=player['strategy']
        )

print("✅ Loaded", len(data), "games into the database!")
