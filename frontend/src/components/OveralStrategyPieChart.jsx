import {
    PieChart, Pie, Tooltip, Cell, Legend, ResponsiveContainer
  } from 'recharts';
  
  const COLORS = ['#8884d8', '#82ca9d', '#ffc658', '#ff7f50'];
  
  export default function OverallStrategyPieChart({ strategies }) {
    if (!strategies || strategies.length === 0) return null;
  
    const data = strategies.map(p => ({
      name: p.strategy,
      value: p.wins,
    }));
  
    return (
       
      <div>
        {/* <h3>win</h3> */}
        <ResponsiveContainer width="100%" height={300}>
          <PieChart>
            <Pie
              data={data}
              cx="50%"
              cy="50%"
              outerRadius={80}
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
  