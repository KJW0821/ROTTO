import axios from "axios";
import TokenService from "./token";
import { useNavigation } from "@react-navigation/native";

const DOMAIN = "https://k10e105.p.ssafy.io"

const API = axios.create({
  baseURL: DOMAIN + "/api",
  withCredentials: true,
});

const reissueAPI = axios.create({
  baseURL: DOMAIN + "/api",
  withCredentials: true,
});

API.interceptors.request.use(
  async (config) => {
    const accessToken = await TokenService.getAccessToken();
    if (accessToken) {
      config.headers['Authorization'] = 'Bearer' + accessToken;
    }
    return config;
  },
  (err) => {
    return Promise.reject(err);
  }
);

let isRefreshing = false;
let refreshPromise = null;

API.interceptors.response.use(
  (res) => {
    return res;
  },
  async (err) => {
    if (err.response && err.response.status === 401) {
      if (!isRefreshing) {
        isRefreshing = true;
        const refreshToken = await TokenService.getRefreshToken();
        const headers = { Authorization: 'Bearer' + refreshToken };

        refreshPromise = await reissueAPI.get('/auth/refresh', { headers })
          .then(async (res) => {
            const access = res.data.accessToken;
            const refresh = res.data.refreshToken;
            await TokenService.setToken(access, refresh);
            return Promise.resolve();
          })
          .catch(async (err) => {
            if (err.response.status === 401) {
              const accessToken = await TokenService.getAccessToken();
              const refreshToken = await TokenService.getRefreshToken();
              const params = { accessToken, refreshToken };
              return await API.post('/auth/logout', { params })
                .then(async () => {
                  await TokenService.clearAllData();
                })
                .catch((err) => {
                  console.error(err);
                })
            }
            return Promise.reject(err);
          })
          .finally(() => {
            isRefreshing = false;
            refreshPromise = null;
          });
      }

      if (refreshPromise) {
        return refreshPromise.then(() => {
          return API(err.config);
        })
      } else {
        return Promise.reject(err);
      }
    }
    return Promise.reject(err);
  }
);

export default API