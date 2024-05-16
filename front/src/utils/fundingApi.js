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

export const applyFunding = async (subscriptionCode, applyCount) => {
  try {
    const res = await API.post(`/apply/${subscriptionCode}`, null, {
      params: {
        applyCount
      }
    });
    return res;
  } catch (err) {
    console.error('청약 신청 실패' + err);
    return err.response;
  }
};