import API from "./Api";

const URL = '/account';

export const getAccountInfo = async () => {
  try {
    const res = await API.get(URL);
    return res.data;
  } catch (err) {
    console.error(err);
    return err;
  }
};

export const getRealAccountInfo = async () => {
  try {
    const res = await API.get(URL + '/real');
    return res.data;
  } catch (err) {
    console.error(err);
    return err;
  }
};