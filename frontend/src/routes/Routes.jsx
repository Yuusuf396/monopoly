import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HomePage from '../pages/HomePage';
import GameDetail from '../pages/GameDetail';
import Strategy from '../pages/Strategy';


export default function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/games/:id" element={<GameDetail />} />
        <Route path="/games/strategy/:strategy" element={ <Strategy/>} />
      </Routes>
    </BrowserRouter>
  );
}
