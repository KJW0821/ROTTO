import {createSlice} from '@reduxjs/toolkit';

const initialState = {
  alertList: null,
};

export const alertSlice = createSlice({
  name: 'alertInfo',
  initialState,
  reducers: {
    setAlertList: (state, action) => {
      // console.log("alertSlice", action.payload)
      state.alertList = action.payload;
    },
  },
});

export const {setAlertList} = alertSlice.actions;

export default alertSlice.reducer;
