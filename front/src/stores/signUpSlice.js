import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  name: "",
  personId: "",
  phoneNumber: ""
}

export const signUpSlice = createSlice({
  name: "signUpInfo",
  initialState,
  reducers: {
    inputName: (state, action) => {
      state.name = action.payload;
    },
    inputPersonId: (state, action) => {
      state.personId = action.payload;
    },
    inputPhoneNumber: (state, action) => {
      state.phoneNumber = action.payload;
    }
  }
});

export const { inputName, inputPersonId, inputPhoneNumber } = signUpSlice.actions;

export default signUpSlice.reducer;