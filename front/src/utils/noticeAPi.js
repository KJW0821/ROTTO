import API from "./Api";
// import axios from "axios";

// const DOMAIN = "https://k10e105.p.ssafy.io";

// const api = axios.create({
//   baseURL: DOMAIN + "/api",
//   withCredentials: true,
//   headers: {
//     Authorization:
//       "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMyIsImlhdCI6MTcxNDk3NDUyOSwiZXhwIjoxNzE0OTc2MzI5fQ.Eoi7acGuKdvratueuIG3hMFy0YYQ08s-B6_1zPK6vlU",
//   },
// });

export const getNoticeList = async (page) => {
  try {
    const res = await API.get("/notice", {
      params: {
        page,
      },
    });
    console.log(res.data);
    return res.data;
  } catch (err) {
    console.error(err);
  }
};

export const getNoticeDetail = async (code) => {
  try {
    const res = await API.get(`/notice/${code}`);
    console.log(res.data);
    return res.data;
  } catch (err) {
    console.error(err);
  }
};
