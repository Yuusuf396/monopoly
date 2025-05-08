// src/GlassTest.jsx

import React from "react";
// import '../output.css'
export default function GlassTest() {
  return (
    <div className="min-h-screen bg-gray-900 text-white flex justify-center items-start pt-20 px-4">
      <div
        className="w-full max-w-2xl p-8
          bg-white bg-opacity-10 
          backdrop-blur-md 
          border border-white border-opacity-20 
          rounded-3xl 
          shadow-xl 
          space-y-6"
      >
        <h1 className="text-3xl font-bold text-center">✨ G lassmorphism Test</h1>

        <p className="text-center text-gray-200">
          If you can see a glowing, semi-transparent card with blur and white
          borders — Tailwind is fully working!
        </p>

        <div className="text-center">
          <button className="bg-blue-500 hover:bg-blue-600 text-white px-6 py-2 rounded-full shadow">
            I see it!
          </button>
        </div>
      </div>
    </div>
  );
}
