import API from "./Api";

const URL = '/subscription';

export const getFundingList = async () => {
  try {
    const res = await API.get(URL);
    return res.data;
  } catch (err) {
    console.error(err);
    return err;
  }
};

export const getFundingDetail = async (subscriptionCode) => {
  try {
    const res = await API.get(URL + `/${subscriptionCode}`);
    return res.data;
  } catch (err) {
    console.error(err);
    return err;
  }
};