import API from "./Api";

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

export const getUserInfo = async () => {
  try {
    const res = await API.get('/user/user');
    return res;
  } catch (err) {
    console.error(err);
    return err;
  }
};