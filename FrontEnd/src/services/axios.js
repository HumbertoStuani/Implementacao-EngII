import axios from 'axios';

// Configuração do cliente API para usuários
const apiClient = axios.create({
  baseURL: process.env.VUE_APP_API_BASE_URL_USER,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Configuração do cliente API para clientes
const apiClientClientes = axios.create({
  baseURL: process.env.VUE_APP_API_URL_CLIENTES,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Configuração do cliente API para produtos
const apiClientProdutos = axios.create({
  baseURL: process.env.VUE_APP_API_URL_PRODUTOS,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Adicionar token de autenticação às requisições
const addAuthToken = (config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
};

// Interceptor para adicionar token de autenticação
apiClient.interceptors.request.use(addAuthToken, (error) => Promise.reject(error));
apiClientClientes.interceptors.request.use(addAuthToken, (error) => Promise.reject(error));
apiClientProdutos.interceptors.request.use(addAuthToken, (error) => Promise.reject(error));

export { apiClientClientes, apiClientProdutos };
export default apiClient;
