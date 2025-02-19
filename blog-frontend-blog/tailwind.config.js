/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  theme: {
    screens:{
      'sm':'640px',
      'md':'768px',
      'lg':'1024px',
      'xl':'1280px',
      '2xl':'1536px',
      'smt':'639px',
      'mdt':'767px',
      'lgt':'1023px',
      'cto':'1259px',
    },
    extend: {
      theme:["light,dark,cupcake"],
    },
  },

  plugins: [require('daisyui')],
}