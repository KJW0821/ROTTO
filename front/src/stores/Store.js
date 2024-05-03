import { configureStore } from "@reduxjs/toolkit";
import signUpSlice from "./signUpSlice";

export const store = configureStore({
  reducer: {
    signupInfo: signUpSlice
  }
});