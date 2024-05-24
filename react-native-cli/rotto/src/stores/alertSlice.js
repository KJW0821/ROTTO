import {createSlice} from '@reduxjs/toolkit';

const initialState = {
  alertList: null,
  isAlert: false,
  fcmToken: "",
};

export const alertSlice = createSlice({
  name: 'alertInfo',
  initialState,
  reducers: {
    // 알림 존재 여부
    setIsAlert: (state, action) => {
      state.isAlert = action.payload;
    },
    // 알림 목록 정보
    setAlertList: (state, action) => {
      state.alertList = action.payload;
    },
    // FCM 기기 토큰
    setFcmToken: (state, action) => {
      state.fcmToken = action.payload;
    },
  },
});

export const {setAlertList, setIsAlert, setFcmToken} = alertSlice.actions;

export default alertSlice.reducer;
