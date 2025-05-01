# monopoly
# 🧠 Monopoly Strategy Simulator

This project simulates and analyzes over 1,000 games of Monopoly to evaluate the performance of different player strategies (Aggressive, Conservative, Random). The simulation results are stored in a Django database, exposed through a REST API, and visualized using Python and React.

---

## 🔍 Project Summary

- Simulates strategic behavior in Monopoly using a Java simulator
- Stores game outcomes in a Django backend via structured JSON ingestion
- Provides an API to retrieve game data and player outcomes
- Visualizes strategy success with charts (bar, pie, boxplot) using Python
- Displays results in a React frontend for a clean, interactive experience

---

## 🧱 Tech Stack

- **Backend:** Django · Django REST Framework
- **Frontend:** React (Vite)
- **Visualization:** Python (pandas, matplotlib, seaborn)
- **Database:** SQLite (local) or PostgreSQL (deployment-ready)
- **Deployment (optional):** Render (for backend) + Netlify/Vercel (for frontend)

---

## 📊 Sample Visualizations

### 🎯 Strategy Success Rates

![Bar Chart](./monopoly_strategy_bar_chart.png)

### 🥧 Win Distribution

![Pie Chart](./monopoly_strategy_pie_chart.png)

---

## ⚙️ Setup Instructions

### 1. Clone and Install

```bash
git clone https://github.com/YOUR_USERNAME/monopoly-strategy-simulator.git
cd monopoly-strategy-simulator
python3 -m venv venv
source venv/bin/activate
pip install -r requirements.txt
