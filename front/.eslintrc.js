module.exports = {
  root: true,
  parserOptions: {
    parser: '@babel/eslint-parser',
  },
  env: {
    node: true,
    browser: true,
    es6: true,
  },
  plugins: ['vue'],
  extends: ['eslint:recommended', 'plugin:vue/vue3-recommended'],
  rules: {
    'no-undef': 'off',
    'no-unused-vars': 'off',
    'no-multiple-template-root': 'off',
    'vue/html-self-closing': 'off',
  },
};
