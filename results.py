import json
import matplotlib.pyplot as plt
from collections import Counter

# Load your JSON data
with open('new_all_results.json', 'r') as f:
    data = json.load(f)

# Extract strategies
strategies = [game['strategy'] for game in data]
strategy_counts = Counter(strategies)

# Prepare data
labels = list(strategy_counts.keys())
values = list(strategy_counts.values())
total_games = sum(values)
percentages = [v / total_games * 100 for v in values]

# ---------- 1. Fancy Bar Chart ----------

plt.figure(figsize=(9, 6))
bars = plt.bar(labels, percentages, color=['#4CAF50', '#2196F3', '#FFC107'])

# Add data labels
for bar, percentage, count in zip(bars, percentages, values):
    yval = bar.get_height()
    plt.text(bar.get_x() + bar.get_width()/2.0, yval + 1, f"{percentage:.1f}%\n({count})", 
             ha='center', va='bottom', fontsize=10)

plt.title('Monopoly Strategy Success Rates', fontsize=16, fontweight='bold')
plt.xlabel('Strategy', fontsize=14)
plt.ylabel('Percentage of Wins (%)', fontsize=14)
plt.ylim(0, max(percentages) + 10)  # Extra space for text labels
plt.xticks(fontsize=12)
plt.yticks(fontsize=12)
plt.tight_layout()

# Save bar chart
plt.savefig('monopoly_strategy_bar_chart.png', dpi=300)
plt.show()

# ---------- 2. Fancy Pie Chart ----------

# Optional custom colors
colors = ['#4CAF50', '#2196F3', '#FFC107']

plt.figure(figsize=(8, 8))
plt.pie(values, labels=labels, autopct='%1.1f%%', startangle=140, colors=colors, textprops={'fontsize': 12})
plt.title('Strategy Win Distribution', fontsize=16, fontweight='bold')
plt.tight_layout()

# Save pie chart
plt.savefig('monopoly_strategy_pie_chart.png', dpi=300)
plt.show()
