// src/api/api.js
import axios from 'axios';

const API = axios.create({
  baseURL: 'http://localhost:8000/api/', // switch to deployed URL for production
});

// Game APIs
export const getGames = (page=1) => API.get(`gameresultlist/?page=${page}`);
export const getGameById = (id) => API.get(`gameresultdetail/${id}/`);
// export const getGameByStrategy = (page = 1, strategy = '') => {
//   // const url = strategy ? `/gameresult/?page=${page}&strategy=${strategy}` : `gameresultlist/?page=${page}`
//   const url = strategy ? `/gameresults/${strategy}?page=${page}/` : `gameresultlist/?page=${page}`

//   return API.get(url);
    
// };

export const getGameByStrategy = (page = 1, strategy = '') => {
  const url = `/gameresults/${strategy}/?page=${page}`;
  return API.get(url);
};

 
export const simulateGame = () => API.post('simulate/');
