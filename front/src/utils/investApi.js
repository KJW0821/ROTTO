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

export const getTradeHistory = async () => {
  try {
    const res = await API.get('/trade-history');
    return res.data;
  } catch (err) {
    console.error(err);
    return err;
  }
};

export const getCancelHistory = async () => {
  try {
    const res = await API.get('/apply/terminated');
    return res.data;
  } catch (err) {
    console.error(err);
    return err;
  }
};

export const getOwnHistory = async () => {
  try {
    const res = await API.get('/trade-history/own');
    return res.data;
  } catch (err) {
    console.error(err);
    return err;
  }
};

export const getOwnDetail = async (subscriptionCode) => {
  try {
    const res = await API.get(`/trade-history/own/${subscriptionCode}`);
    return res.data;
  } catch (err) {
    console.error(err);
    return err;
  }
};