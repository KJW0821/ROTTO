import API from "./Api";

export const getAlertList = async () => {
  try {
    const res = await API.get("");
    console.log(res.data);
    return res.data;
  } catch (err) {
    console.error(err);
  }
};

export const getAlertDetail = async (code) => {
  try {
    const res = await API.get(`${code}`);
    console.log(res.data);
    return res.data;
  } catch (err) {
    console.error(err);
  }
};

export const deleteAlert = async (code) => {
  try {
    const res = await API.delete(`/${code}`);
    console.log(res.data);
    return res.data;
  } catch (err) {
    console.error(err);
  }
};

export const readAllAlert = async () => {
  try {
    const res = await API.put("/all-read");
    console.log(res.data);
    return res.data;
  } catch (err) {
    console.error(err);
  }
};

export const deleteAllAlert = async () => {
    try {
      const res = await API.delete("/all-delete");
      console.log(res.data);
      return res.data;
    } catch (err) {
      console.error(err);
    }
  };