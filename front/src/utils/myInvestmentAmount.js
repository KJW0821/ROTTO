import API from "./Api";

export const getMyInvestment = async () => {
  try {
    const res = await API.get("/trade-history/home");
    console.log(res.data);
    return res.data;
  } catch (err) {
    console.error(err);
  }
};