import {
    AreaChart,
    Area,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    ResponsiveContainer,
    Legend
  } from 'recharts';
  
  export default function StrategyAreaChart({ strategies }) {
    if (!strategies || strategies.length === 0) return null;
    console.log(strategies);
  
    //  const chartData = [
    //   strategies.reduce(
    //     (acc, item) => ({ ...acc, [item.strategy]: item.wins }),
    //     { name: 'Strategies' }
    //   ),
    // ];
    const chartData = [
      { name: 'Game 1', AGGRESSIVE: 10, RANDOM: 5, CONSERVATIVE: 2 },
      { name: 'Game 2', AGGRESSIVE: 7, RANDOM: 8, CONSERVATIVE: 3 },
      { name: 'Game 3', AGGRESSIVE: 6, RANDOM: 4, CONSERVATIVE: 9 },
      { name: 'Game 4', AGGRESSIVE: 12, RANDOM: 6, CONSERVATIVE: 7 },
    ];
    
    
    return (
      <div className="area-chart-box">
        {/* <h3>📊 Strategy Wins (Stacked Area)</h3> */}
        <ResponsiveContainer width="100%" height={250}>
          <AreaChart
            data={chartData}
            margin={{ top: 10, right: 30, left: 0, bottom: 0 }}
          >
            <defs>
              <linearGradient id="aggressive" x1="0" y1="0" x2="0" y2="1">
                <stop offset="5%" stopColor="#f87171" stopOpacity={0.8} />
                <stop offset="95%" stopColor="#f87171" stopOpacity={0} />
              </linearGradient>
              <linearGradient id="random" x1="0" y1="0" x2="0" y2="1">
                <stop offset="5%" stopColor="#60a5fa" stopOpacity={0.8} />
                <stop offset="95%" stopColor="#60a5fa" stopOpacity={0} />
              </linearGradient>
              <linearGradient id="conservative" x1="0" y1="0" x2="0" y2="1">
                <stop offset="5%" stopColor="#34d399" stopOpacity={0.8} />
                <stop offset="95%" stopColor="#34d399" stopOpacity={0} />
              </linearGradient>
            </defs>
  
            <XAxis dataKey="name" />
            <YAxis allowDecimals={false} />
            <CartesianGrid strokeDasharray="3 3" />
            <Tooltip />
            <Legend />
            <Area type="monotone" dataKey="AGGRESSIVE" stroke="#f87171" fill="url(#aggressive)" />
            <Area type="monotone" dataKey="RANDOM" stroke="#60a5fa" fill="url(#random)" />
            <Area type="monotone" dataKey="CONSERVATIVE" stroke="#34d399" fill="url(#conservative)" />
          </AreaChart>
        </ResponsiveContainer>
      </div>
    );
  }
  