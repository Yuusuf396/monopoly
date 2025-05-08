/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",  // ✅ enables Tailwind in your React components
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}
