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
    if (err.response.status === 500) {
      return null;
    }
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

export const connectAccount = async (data) => {
  try {
    const res = await API.post(URL + '/connection', data);
    return res;
  } catch (err) {
    console.error('계좌 연결 실패 ' + err);
    return err.response;
  }
};