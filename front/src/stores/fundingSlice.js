import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  isApplyModalOpen: false,
  fundingData: null
}

export const fundingSlice = createSlice({
  name: "fundingInfo",
  initialState,
  reducers: {
    setApplyModal: (state, action) => {
      state.isApplyModalOpen = action.payload;
    },
    setFundingData: (state, action) => {
      state.fundingData = action.payload;
    }
  }
});

export const { 
  setApplyModal,
  setFundingData
} = fundingSlice.actions;

export default fundingSlice.reducer;