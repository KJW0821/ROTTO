import API from "./Api";

export const signUp = async (data) => {
  try {
    const res = await API.post('/user/signup', data);
    return res;
  } catch (err) {
    console.log(err);
  }
};

export const signIn = async (data) => {
  try {
    const res = await API.post('/auth/login', data);
    return res;
  } catch (err) {
    console.log(err);
    return err;
  }
};