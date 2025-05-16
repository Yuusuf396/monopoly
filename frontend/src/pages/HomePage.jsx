import { useEffect, useState ,} from 'react';
import { getGameByStrategy, getGames,getStrategy,simulateGame } from '../api/api';
import { Link, useNavigate} from 'react-router-dom';
import Strategy from './Strategy';
import Card from '../components/Card';

import '../styles/home.css'
import OverallStrategyPieChart from '../components/OveralStrategyPieChart';
import OverallStrategyLineChart from '../components/OveralStrategyLineChart';
import StrategyAreaChart from '../components/StrategyAreaChart';
import StrategyBarChart from '../components/StrategyBarChart';
 

export default function HomePage() {

    const strategies = ['', 'AGGRESSIVE', 'CONSERVATIVE', 'RANDOM'];

    const navigate = useNavigate();
    const [selectedStrategy, setSelectedStrategy] = useState('');
    const [games, setGames] = useState([]);
    
    const [strategywins, setStrategyWins] = useState([]);
    const [loading, setLoading] = useState(null);
    const [currentPage, setCurrentPage] = useState(1);
    const [next, setNext] = useState(null);
    const [prev, setPrev] = useState(null);


    const fetchGames = async (strategy) => {
        setLoading(true);
        
        try {
            const res = strategy
                // console.log(res.data);
            ? await getGameByStrategy(currentPage, strategy) : await getGames(currentPage);
          
            setGames(res.data.results || res.data)
            setNext(res.data.next);
          setPrev(res.data.previous);
          // console.log(games);
          // console.log(selectedStrategy);

        } catch (e) {
            console.error(e);
        }
        finally {
            
            setLoading(false);
        }
  };
  

  const fetchStrategyWins=async()=> {
    setLoading(true);
    try {
      const res = await getStrategy();
      setStrategyWins(res.data);
      console.log(res.data[0]);

    }
    catch (e) {
      console.error(e);
    }
    finally {
      setLoading(false);
    }
  }

  const simulateNew = async () => {
    try  {
      const response = await simulateGame();
      
      console.log('Created post:', response.data);
    } catch (error) {
      console.error('Failed to create post:', error);
    }
  
  }

    useEffect(() => {
      fetchGames(selectedStrategy);
      fetchStrategyWins();
    }, [currentPage,selectedStrategy]);

    
  
  return (
    <div className="page-wrapper">

      <div className="main-card">
          <div className="chart-row">
              <div className="mini-chart-box">
                 <OverallStrategyPieChart strategies={strategywins} /> 
              </div>
              <div className="mini-chart-box">
                <StrategyBarChart strategies={strategywins} />
              </div>

              {/* <div className="mini-chart-box">
                <OverallStrategyLineChart strategies={strategywins} />
          </div> */}
          {/* <div className="mini-chart-box"><StrategyAreaChart strategies={strategywins} /></div> */}
        </div>
        <div className='simulate' onClick={simulateNew}>
          <button>Simulate New Data</button>
        </div>
        

        
        <h1 className="title">Simulated Games</h1>

        <label className="filter-label">Filter by Strategy</label>
        <div className="strategy-buttons">
          {strategies.map((strategy) => (
            <button
              key={strategy || 'ALL'}
              onClick={() => {
                setSelectedStrategy(strategy);
                setCurrentPage(1);
              }}
              className={`strategy-button ${selectedStrategy === strategy ? 'active' : ''}`}
            >
              {strategy === '' ? 'All' : strategy}
            </button>
          ))}
        </div>

        <div className="game-list">
          {loading ? (
            <p className="loading">Loading…</p>
          ) : (
            games.map((game) => (
              <div
                key={game.id}
                className="game-card"
                onClick={() => navigate(`/games/${game.id}`)}
              >
                <div>TO ID {game.id}</div>
                <p>
                  <strong>{game.winner}</strong> won in {game.turns} turns using{' '}
                  <em>{game.strategy}</em>.
                </p>
              </div>
            ))
          )}
        </div>
        {/* {strategywins.map((strategy) => (<p key={strategy.strategy}>{strategy.strategy}:{ strategy.wins}</p> ))} */}
      
        
        <div className="pagination">
       
          <button onClick={() => setCurrentPage((p) => p - 1)} disabled={!prev}>
            Previous
          </button>
          <button onClick={() => setCurrentPage((p) => p + 1)} disabled={!next}>
            Next
          </button>
        </div>
      </div>
    </div>
  );
}
  
  
 
 

