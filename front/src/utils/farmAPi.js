import API from "./Api";

export const getFarmList = async (sort, keyword, ) => {
  try {
    const res = await API.get("/farm",{
      params: {
               
      },
    });
    console.log(res.data);
    return res.data;
  } catch (err) {
    console.error(err);
  }
};

export const getFarmDetail = async (code) => {
  try {
    const res = await API.get(`/farm/${code}`);
    console.log(res.data);
    return res.data;
  } catch (err) {
    console.error(err);
  }
};
