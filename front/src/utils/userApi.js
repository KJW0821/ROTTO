import API from "./Api";

const URL = '/user';

export const signUp = async (data) => {
  try {
    const res = await API.post(URL + '/signup', data);
    return res;
  } catch (err) {
    console.log(err);
  }
};