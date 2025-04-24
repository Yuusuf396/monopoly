import { useEffect, useState } from 'react';
import { getGames } from '../api/api';
import { Link } from 'react-router-dom';

export default function HomePage() {
    const [games, setGames] = useState([]);
    const [loading, setLoading] = useState(null);
    const [currentPage, setCurrentPage] = useState(1);
    const [next, setNext] = useState(null);
    const [prev, setPrev] = useState(null);

    useEffect(() => {
      setLoading(true)
        getGames(currentPage).then(res => {
            setGames(res.data.results);
            console.log(res.data);

            setNext(res.data.next);
            setPrev(res.data.previous);
            setLoading(false);
        });
    }, [currentPage]);

  return (
    <div className="p-4">
    <h1 className="text-2xl font-bold mb-4">Simulated Games</h1>
    {/* <Link to={games} className='text-2xl font-bold mb' >TO ID</Link> */}
       <div className="space-y-2">
       {loading ? (
        <p className="animate-pulse text-gray-500">Loading Pokémon...</p>
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
