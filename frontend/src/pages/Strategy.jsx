import { useEffect, useState, } from 'react';
import { getGameByStrategy } from '../api/api';
import { Link ,useParams,useNavigate} from 'react-router-dom';


export default function Strategy() {


    const { strategy } = useParams();
    const navigate = useNavigate();
    const [games, setGames] = useState([]);
    const [loading, setLoading] = useState(null);
    const [currentPage, setCurrentPage] = useState(1);
    const [next, setNext] = useState(null);
    const [prev, setPrev] = useState(null);
    // const [strategy, setStrategy] = useState(null);
  

    useEffect(() => {
      setLoading(true)
        getGameByStrategy(currentPage,strategy).then(res => {
            setGames(res.data.results);
            console.log(res.data);

            setNext(res.data.next);
            setPrev(res.data.previous);
            setLoading(false);
        });
    }, [currentPage,strategy]);

  return (
    <div className="p-4">
          <h1 className="text-2xl font-bold mb-4">Simulated Games</h1>
      <label className="block mb-1 text-sm font-medium text-gray-700">Filter by Strategy: {strategy}</label>

       
    {/* <Link to={games} className='text-2xl font-bold mb' >TO ID</Link> */}
       <div className="space-y-2">
       {loading ? (
        <p className="animate-pulse text-gray-500">Loading .....</p>
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
        <button
          onClick={() => navigate('/')}
         
          className="bg-blue-500 text-white px-4 py-1 rounded disabled:opacity-50"
        >
          Home
          </button>
      </div>
      </div>
    </div>
  );
}


// import { useEffect, useState } from 'react';
// import { getGameByStrategy } from '../api/api';
// import { Link, useParams } from 'react-router-dom';

// export default function Strategy() {
//   const { strategy } = useParams(); // ✅ Get strategy from URL
//   const [games, setGames] = useState([]);
//   const [loading, setLoading] = useState(null);
//   const [currentPage, setCurrentPage] = useState(1);
//   const [next, setNext] = useState(null);
//   const [prev, setPrev] = useState(null);

//   useEffect(() => {
//     setLoading(true);
//     getGameByStrategy(currentPage, strategy).then((res) => {
//       setGames(res.data.results);
//       setNext(res.data.next);
//       setPrev(res.data.previous);
//       setLoading(false);
//     });
//   }, [currentPage, strategy]);

//   return (
//     <div className="p-4">
//       <h1 className="text-2xl font-bold mb-4">Simulated Games</h1>
//       <p className="mb-3 text-gray-700">
//         Showing games with strategy: <span className="font-semibold">{strategy}</span>
//       </p>

//       {loading ? (
//         <p className="animate-pulse text-gray-500">Loading…</p>
//       ) : (
//         <ul className="space-y-2">
//           {games.length === 0 ? (
//             <p className="text-gray-400 italic">No games found for this strategy.</p>
//           ) : (
//             games.map((game) => (
//               <li key={game.id} className="border p-3 rounded bg-white shadow-sm">
//                 <Link
//                   to={`/games/${game.id}`}
//                   className="text-blue-600 underline block mb-1"
//                 >
//                   View Game #{game.id}
//                 </Link>
//                 <p>
//                   <strong>{game.winner}</strong> won in {game.turns} turns using{' '}
//                   <em>{game.strategy}</em>.
//                 </p>
//               </li>
//             ))
//           )}
//         </ul>
//       )}

//       {/* Pagination Controls */}
//       <div className="flex gap-4 mt-4">
//         <button
//           onClick={() => setCurrentPage((p) => p - 1)}
//           disabled={!prev}
//           className="bg-gray-200 px-4 py-1 rounded disabled:opacity-50"
//         >
//           Previous
//         </button>
//         <button
//           onClick={() => setCurrentPage((p) => p + 1)}
//           disabled={!next}
//           className="bg-blue-500 text-white px-4 py-1 rounded disabled:opacity-50"
//         >
//           Next
//         </button>
//       </div>
//     </div>
//   );
// }
