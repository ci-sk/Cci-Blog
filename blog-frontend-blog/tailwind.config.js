/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  theme: {
    screens: {
      'sm': '640px',
      'md': '768px',
      'lg': '1024px',
      'xl': '1280px',
      '2xl': '1536px',
      'smt': '639px',
      'mdt': '767px',
      'lgt': '1023px',
      'cto': '1259px',
    },
    extend: {}
  },
  daisyui: {
    // themes: ['light', 'dark', 'cupcake'],
    themes: [
      {
        // text-neutral
        dark: {
          "primary": "#3D5A80",    // 亮红色
          "secondary": "#8795A1",  // 中灰蓝色
          "accent": "#2D3436",     // 深灰色
          "neutral": "#cee8ff",    // 深蓝色
          "base-100": "#1B1F2E",   // 最深的蓝色
          "base-200": "#2C3444",   // 深蓝灰色
          "base-300": "#3D4558",   // 中蓝灰色
          "info": "#8795A1",       // 信息色（灰蓝）
          "success": "#36D399",    // 成功色保持默认
          "warning": "#FBBD23",    // 警告色保持默认
          "error": "#F87272",      // 错误色保持默认
        },
        light: {
          "primary": "#67B7D1",    // 天蓝色
          "secondary": "#006D87",  // 深蓝色
          "accent": "#2D3436",     // 深灰色
          "neutral": "#A3B5BD",    // 灰蓝色
          "base-100": "#F0F6F9",   // 最浅的蓝色
          "base-200": "#E1E9ED",   // 浅灰蓝色
          "base-300": "#D1DCE2",   // 中灰蓝色
          "info": "#67B7D1",       // 信息色（天蓝）
          "success": "#36D399",    // 成功色保持默认
          "warning": "#FBBD23",    // 警告色保持默认
          "error": "#F87272",      // 错误色保持默认
        },
      }
    ]
  },
  plugins: [require('daisyui')],
}