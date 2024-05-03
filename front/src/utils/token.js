import * as SecureStore from 'expo-secure-store'

const StorageKeys = {
  AccessToken: null,
  RefreshToken: null
};

const TokenService = {
  // 토큰 저장
  setToken: async (accessToken, refreshToken) => {
    try {
      await SecureStore.setItemAsync(StorageKeys.AccessToken, accessToken);
      await SecureStore.setItemAsync(StorageKeys.RefreshToken, refreshToken);
    } catch (error) {
      console.error('토큰 저장 실패', error);
    }
  },

  // 액세스 토큰 조회
  getAccessToken: async () => {
    try {
      return await SecureStore.getItemAsync(StorageKeys.AccessToken);
    } catch (error) {
      console.error('accessToken 조회 실패', error);
      return null;
    }
  },

  // 리프레시 토큰 조회
  getRefreshToken: async () => {
    try {
      return await SecureStore.getItemAsync(StorageKeys.RefreshToken);
    } catch (error) {
      console.error('refreshToken 조회 실패', error);
      return null;
    }
  },

  // 모든 정보 삭제
  clearAllData: async () => {
    try {
      await SecureStore.deleteItemAsync(StorageKeys.AccessToken);
      await SecureStore.deleteItemAsync(StorageKeys.RefreshToken);
    } catch (error) {
      console.error(error);
    }
  },
};


export default TokenService;
