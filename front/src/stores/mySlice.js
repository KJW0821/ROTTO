import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  isWalletModalOpen: false
}

export const mySlice = createSlice({
  name: "myPageInfo",
  initialState,
  reducers: {
    setWalletModal: (state, action) => {
      state.isWalletModalOpen = action.payload;
    }
  }
});

export const { setWalletModal } = mySlice.actions;

export default mySlice.reducer;