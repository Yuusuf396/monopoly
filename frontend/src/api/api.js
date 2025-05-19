// src/api/api.js
import axios from 'axios';

const API = axios.create({
  baseURL: 'https://monopoly-backend-jcp2.onrender.com/',  
});

// Game APIs
export const getGames = (page=1) => API.get(`api/gameresultlist/?page=${page}`);
export const getGameById = (id) => API.get(`api/gameresultdetail/${id}/`);
// export const getGameByStrategy = (page = 1, strategy = '') => {
//   // const url = strategy ? `/gameresult/?page=${page}&strategy=${strategy}` : `gameresultlist/?page=${page}`
//   const url = strategy ? `/gameresults/${strategy}?page=${page}/` : `gameresultlist/?page=${page}`

//   return API.get(url);
    
// };

export const getStrategy =()=>API.get(`api/gameresultlistbystrategy/`)
export const getGameByStrategy = (page = 1, strategy = '') => {
  const url = `api/gameresults/${strategy}/?page=${page}`;
  return API.get(url);
};

 
export const simulateGame = () => API.post('simulate/');
