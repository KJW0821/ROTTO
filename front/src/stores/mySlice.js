import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  isWalletModalOpen: false,
  isBankModalOpen: false,
  selectedBank: null,
  connectedAccount: null,
  transactionMode: null,
  isFilterModalOpen: null,
  selectedFilter: '전체',
  fundingAccount: null
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
    },
    setTransactionMode: (state, action) => {
      state.transactionMode = action.payload;
    },
    setFilterModal: (state, action) => {
      state.isFilterModalOpen = action.payload;
    },
    setSelectedFilter: (state,action) => {
      state.selectedFilter = action.payload;
    },
    setFundingAccount: (state, action) => {
      state.fundingAccount = action.payload;
    }
  }
});

export const { 
  setWalletModal, 
  setBankModal, 
  setSelectedBank, 
  setConnectedAccount, 
  setTransactionMode, 
  setFilterModal,
  setSelectedFilter,
  setFundingAccount
} = mySlice.actions;

export default mySlice.reducer;