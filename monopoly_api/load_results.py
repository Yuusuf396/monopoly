import os
import django
import json


from django.db import connection
 

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


GameResult.objects.all().delete()
PlayerData.objects.all().delete()

# Reset primary key sequence (SQLite)
with connection.cursor() as cursor:
    cursor.execute("DELETE FROM sqlite_sequence WHERE name='games_gameresult';")
    cursor.execute("DELETE FROM sqlite_sequence WHERE name='games_playerdata';")

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
