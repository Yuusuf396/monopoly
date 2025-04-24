import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HomePage from '../pages/HomePage';
import GameDetail from '../pages/GameDetail';

export default function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/games/:id" element={<GameDetail />} />
      </Routes>
    </BrowserRouter>
  );
}
