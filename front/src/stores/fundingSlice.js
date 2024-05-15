import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  isApplyModalOpen: false
}

export const fundingSlice = createSlice({
  name: "fundingInfo",
  initialState,
  reducers: {
    setApplyModal: (state, action) => {
      state.isApplyModalOpen = action.payload;
    }
  }
});

export const { 
  setApplyModal
} = fundingSlice.actions;

export default fundingSlice.reducer;