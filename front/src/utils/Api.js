import axios from "axios";

const DOMAIN = "https://k10e105.p.ssafy.io"

const API = axios.create({
  baseURL: DOMAIN + "/api",
  withCredentials: true,
});

export default API