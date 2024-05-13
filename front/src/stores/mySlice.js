import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  isWalletModalOpen: false,
  isBankModalOpen: false,
  selectedBank: null,
  connectedAccount: null
}

export const mySlice = createSlice({
  name: "myPageInfo",
  initialState,
  reducers: {
    setWalletModal: (state, action) => {
      state.isWalletModalOpen = action.payload;
    },
    setBankModal: (state, action) => {
      state.isBankModalOpen = action.payload;
    },
    setSelectedBank: (state, action) => {
      state.selectedBank = action.payload;
    },
    setConnectedAccount: (state, action) => {
      state.connectedAccount = action.payload;
    }
  }
});

export const { setWalletModal, setBankModal, setSelectedBank, setConnectedAccount } = mySlice.actions;

export default mySlice.reducer;