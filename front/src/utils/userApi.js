import API from "./Api";
import TokenService from "./token";

export const signUp = async (data) => {
  try {
    const res = await API.post('/user/signup', data);
    return res;
  } catch (err) {
    console.error(err);
  }
};

export const signIn = async (data) => {
  try {
    const res = await API.post('/auth/login', data);
    return res;
  } catch (err) {
    console.error(err);
    return err;
  }
};