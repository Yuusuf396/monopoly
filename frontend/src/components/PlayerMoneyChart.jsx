import {
    PieChart, Pie, Tooltip, Cell, Legend, ResponsiveContainer
  } from 'recharts';
  
  const COLORS = ['#8884d8', '#82ca9d', '#ffc658', '#ff7f50'];
  
  export default function PlayerMoneyChart({ players }) {
    if (!players || players.length === 0) return null;
  
    const data = players.map(p => ({
      name: p.name,
      value: p.money,
    }));
  
    return (
      <div className="chart-section">
        <h3>💰 Money Distribution</h3>
        <ResponsiveContainer width="100%" height={300}>
          <PieChart>
            <Pie
              data={data}
              cx="50%"
              cy="50%"
              outerRadius={100}
              fill="#8884d8"
              dataKey="value"
              label
            >
              {data.map((_, index) => (
                <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
              ))}
            </Pie>
            <Tooltip />
            <Legend />
          </PieChart>
        </ResponsiveContainer>
      </div>
    );
  }
  