import { useEffect, useState } from 'react';
import { getGameByStrategy, getGames } from '../api/api';
import { Link } from 'react-router-dom';
import Strategy from './Strategy';

export default function HomePage() {

    const strategies = ['', 'AGGRESSIVE', 'CONSERVATIVE', 'RANDOM'];

    // const navigate = useNavigate();
    const [selectedStrategy, setSelectedStrategy] = useState('');
    const [games, setGames] = useState([]);
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

        } catch (e) {
            console.error(e);
        }
        finally {
            
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchGames(selectedStrategy);
    }, [currentPage,selectedStrategy]);

    
  return (
    <div className="p-4">
        <h1 className="text-2xl font-bold mb-4">Simulated Games</h1>
        <label className="block mb-2 text-sm font-medium text-gray-700">
          Filter by Strategy:
        </label>
        <div className="flex flex-wrap gap-2 mb-6">
        {strategies.map((strategy) => (
          <button
            key={strategy || 'ALL'}
                onClick={() => { 
                    setSelectedStrategy(strategy)
                    setCurrentPage(1);
                } }
            className={`px-4 py-2 rounded-full border text-sm font-medium ${
              selectedStrategy === strategy
                ? 'bg-blue-500 text-white border-blue-500'
                : 'bg-gray-100 text-gray-700 border-gray-300'
            }`}
          >
            {strategy === '' ? 'All' : strategy}
          </button>
        ))}
      </div>
    {/* <Link to={games} className='text-2xl font-bold mb' >TO ID</Link> */}
       <div className="space-y-2">
       {loading ? (
        <p className="animate-pulse text-gray-500">Loading ......</p>
      ) : (     
        games.map(game => (
             
          
            <li key={game.id} className="border p-3 rounded bg-white shadow-sm">
                <Link to={`games/${game.id}`} className='text-2xl font-bold mb' >TO ID</Link>
                <strong>{ game.id}:={game.winner}</strong> won in {game.turns} turns using <em>{game.strategy}</em>.
          </li>
        )))}
        
        <div className="flex gap-4">
        <button
          onClick={() => setCurrentPage(p => p - 1)}
          disabled={!prev}
          className="bg-gray-200 px-4 py-1 rounded disabled:opacity-50"
        >
          Previous
        </button>
        <button
          onClick={() => setCurrentPage(p => p + 1)}
          disabled={!next}
          className="bg-blue-500 text-white px-4 py-1 rounded disabled:opacity-50"
        >
          Next
        </button>
      </div>
      </div>
    </div>
  );
}
