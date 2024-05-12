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

export const disConnectAccount = async (accountCode) => {
  try {
    const res = await API.delete(URL + `/real/remove/${accountCode}`);
    return res;
  } catch (err) {
    console.error(err);
    return err;
  }
};