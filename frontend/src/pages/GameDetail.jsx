// import { useEffect, useState } from 'react';
// import { getGameById } from '../api/api';
// // import { use } from 'react';

// function GameDetail() {
//     const [game, setGame] = useState([]);

//     useEffect(() => {
//         getGameById().then(res => { 
//             setGame(res.data)
//         })
//     })
//     return (
//         <div className="p-4">
//             <h1 className="text-2xl font-bold mb-4">Simulated Games</h1>
//             <ul className="space-y-2">
                 
//                     <li key={game.id} className="border p-3 rounded bg-white shadow-sm">
//                         <strong>{game.winner}</strong> won in {game.turns} turns using <em>{game.strategy}</em>.
//                     </li>
//                 {/* ))} */}
//             </ul>
//         </div>
//     );
// }

// export default GameDetail;


// src/pages/GameDetail.jsx
import { useParams } from 'react-router-dom'
import { useEffect, useState } from 'react'
import { getGameById } from '../api/api'

export default function GameDetail() {
  const { id } = useParams()
  const [game, setGame] = useState(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    getGameById(id).then(res => {
      setGame(res.data)
      setLoading(false)
    })
  }, [id])

  if (loading) return <p className="p-4">Loading game...</p>
  if (!game) return <p className="p-4 text-red-500">Game not found.</p>

  return (
    <div className="p-4">
      <h1 className="text-xl font-bold mb-2">Game #{game.id}</h1>
      <p><strong>Winner:</strong> {game.winner}</p>
      <p><strong>Strategy:</strong> {game.strategy}</p>
      <p><strong>Turns:</strong> {game.turns}</p>

      <h2 className="text-lg font-semibold mt-4 mb-2">Players:</h2>
      <ul className="list-disc list-inside">
        {game.players.map((player, index) => (
          <li key={index}>
            {player.name} - 💰 ${player.money} - 🧠 {player.strategy}
          </li>
        ))}
      </ul>
    </div>
  )
}
