import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    ResponsiveContainer,
    Legend
  } from 'recharts';
  
  export default function OverallStrategyLineChart({ strategies }) {
    if (!strategies || strategies.length === 0) return null;
  
    const data = strategies.map(s => ({
      strategy: s.strategy,
      wins: s.wins,
    }));
  
    return (
      <div className="chart-section">
        <h3>📈 Strategy Win Trend</h3>
        <ResponsiveContainer width="100%" height={300}>
          <LineChart data={data} margin={{ top: 20, right: 30, bottom: 5, left: 0 }}>
            <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
            <XAxis dataKey="strategy" />
            <YAxis allowDecimals={false} />
            <Tooltip />
            <Legend />
            <Line
              type="monotone"
              dataKey="wins"
              stroke="#8884d8"
              strokeWidth={2}
              activeDot={{ r: 6 }}
            />
          </LineChart>
        </ResponsiveContainer>
      </div>
    );
  }
  