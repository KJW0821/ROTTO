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

export const getUserInfo = async () => {
  try {
    const res = await API.get('/user/user');
    return res;
  } catch (err) {
    console.error('유저 정보 조회 실패' + err);
    return err;
  }
};

export const logout = async () => {
  try {
    const accessToken = await TokenService.getAccessToken();
    const refreshToken = await TokenService.getRefreshToken();
    const data = {
      accessToken: accessToken,
      refreshToken: refreshToken
    };

    await API.post('/auth/logout', data);
  } catch (err) {
    console.error('로그아웃 에러' + err);
    return err;
  }
};