import API from "./Api";

export const getFarmList = async (sort, keyword, ) => {
  try {
    const res = await API.get("/farm",{
      params: {
               
      },
    });
    return res.data;
  } catch (err) {
  }
};

export const getFarmDetail = async (code) => {
  try {
    const res = await API.get(`/farm/${code}`);
    return res.data;
  } catch (err) {
  }
};

export const getFarmTOP10 = async () => {
  try {
    const res = await API.get("/farm/top-ten");
    return res.data;
  } catch (err) {
  }
};