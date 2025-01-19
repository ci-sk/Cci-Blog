/** @type {import('tailwindcss').Config} */
import daisyui from "daisyui";
module.exports = {
  content: ['./index.html', './src/**/*.{vue,js,ts}'],
  theme: {
    extend: {},
  },
  plugins: [
      daisyui],
}