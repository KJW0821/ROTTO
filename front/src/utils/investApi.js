import API from "./Api";

export const getApplyHistory = async () => {
  try {
    const res = await API.get('/apply');
    return res.data;
  } catch (err) {
    console.error(err);
    return err;
  }
};
