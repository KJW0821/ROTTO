import API from "./Api";

export const getFarmILikeList = async (page) => {
  try {
    const res = await API.get("/farm?is-liked=true", {
        params: {
          page,
        },
      });
    return res;
  } catch (err) {
    console.error(err);
  }
};

export const getFarmILikeDetail = async (farmCode) => {
  try {
    const res = await API.get(`/farm/${farmCode}`);
    return res;
  } catch (err) {
    console.error(err);
  }
};
