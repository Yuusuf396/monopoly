
# 🧠 Monopoly Strategy Simulator

This project simulates 1,000+ games of Monopoly to compare player strategies (Aggressive, Conservative, Random). I used Django to store the results, a simple API to expose them, and React to display the insights. I also used Python and matplotlib to generate graphs.

---

## 🔧 Backend Setup (Django)

```bash
# Clone the repo and go into the backend folder
git clone https://github.com/YOUR_USERNAME/monopoly-strategy-simulator.git
cd monopoly-strategy-simulator/monopoly_backend

# Set up virtual environment
python3 -m venv venv
source venv/bin/activate  # (on Windows: venv\Scripts\activate)

# Install backend packages
pip install -r requirements.txt
