import {
    BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer
  } from 'recharts';
  
  export default function StrategyBarChart({ strategies }) {
    if (!strategies || strategies.length === 0) return null; 
    
  
    return (
      <div  >
            <ResponsiveContainer width="100%" height={300}>
              <BarChart data={strategies}>
                <CartesianGrid strokeDasharray="3 3" />
                  <XAxis
                    dataKey="strategy"
                    interval={0}
                    tick={{ fill: "#fff", fontSize: 12, angle: -30 }}
                    axisLine={{ stroke: "#888" }}
                    tickLine={{ stroke: "#888" }}
                  />

                  <YAxis allowDecimals={false} />
                  <Tooltip />
                  <Legend /> 
                  <Bar
                    dataKey="wins"
                    fill="#8884d8"
                            label={{ position: "top", fill: "#fff", fontSize: 14 }}  
                            
                  />
              </BarChart>
            </ResponsiveContainer>

      </div>
    );
  }
  