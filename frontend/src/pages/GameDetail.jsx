import '../styles/GameDetail.css';
// import '../home.css'
// import '../index.css'


import { Link, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { getGameById } from '../api/api';
import PlayerMoneyChart from '../components/PlayerMoneyChart';

export default function GameDetail() {
  const { id } = useParams();
  const [game, setGame] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getGameById(id)
      .then(res => {
        setGame(res.data);
        setLoading(false);
      })
      .catch(() => {
        setGame(null);
        setLoading(false);
      });
  }, [id]);

  if (loading) return <p className="game-loading">Loading game...</p>;
  if (!game) return <p className="game-error">Game not found.</p>;

  return (
    <div className="game-wrapper">
      <div className="game-card-detail">
        <h1 className="game-title">Game #{game.id}</h1>

        <div className="game-info">
          <p><strong>Winner:</strong> {game.winner}</p>
          <p><strong>Strategy:</strong> {game.strategy}</p>
          <p><strong>Turns:</strong> {game.turns}</p>
        </div>

        <div className="player-section">
          <h2 className="players-title">Players</h2>
          <ul className="player-list">
            {game.players.map((player, index) => (
              <li key={index}>
                {player.name} — 💰 ${player.money} — 🧠 {player.strategy}
              </li>
            ))}
          </ul>
          <PlayerMoneyChart players={game.players}/>
        </div>

        <Link to="/" className="back-link">← Back to all games</Link>
      </div>
    </div>
  );
}
