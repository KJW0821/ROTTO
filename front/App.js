import { StatusBar } from "expo-status-bar";
import { StyleSheet, SafeAreaView, Alert } from "react-native";
import { NavigationContainer } from "@react-navigation/native";
import { store } from "./src/stores/Store";
import { Provider } from "react-redux";
import { SafeAreaProvider } from "react-native-safe-area-context";
import AuthRouters from "./src/routers/AuthRouters";
import Constants from "expo-constants";
import { useFonts } from "expo-font";
import Colors from "./src/constants/Colors";
import { GestureHandlerRootView } from "react-native-gesture-handler";
import BottomSheet from "./src/components/common/MyBottomSheet";
import "@walletconnect/react-native-compat";
import { WagmiConfig, configureChains, createConfig } from "wagmi";
import { mainnet, polygon, arbitrum } from "viem/chains";
import {
  Web3Modal,
  createWeb3Modal,
  defaultWagmiConfig,
} from "@web3modal/wagmi-react-native";
import { defineChain } from "viem";
import { jsonRpcProvider } from "wagmi/providers/jsonRpc";
import { useEffect } from "react";

const customChain = {
  id: 31221,
  name: "ssafy",
  rpcUrls: {
    public: {
      http: [process.env.EXPO_PUBLIC_RPC_URL],
    },
  },
  nativeCurrency: {
    name: "Ether",
    symbol: "ETH",
    decimals: 18,
  },
  testnet: false,
};

const projectId = process.env.EXPO_PUBLIC_PROJECT_ID;

const metadata = {
  name: "rotto",
  description: "커피 STO 투자 증권 앱",
  url: "exp://192.168.30.140:8081",
  icons: ["../../../assets/images/skyIcon.png"],
  redirect: {
    native: "exp://192.168.30.140:8081",
  },
};

const chains = [mainnet];

const wagmiConfig = defaultWagmiConfig({ chains, projectId, metadata });

const { publicClient } = configureChains(
  [customChain],
  [jsonRpcProvider({ rpc: () => ({ http: customChain.rpcUrls.public.http }) })]
);

createConfig({
  autoConnect: true,
  publicClient,
});

createWeb3Modal({
  projectId,
  chains,
  wagmiConfig,
  enableAnalytics: true,
});

export default function App() {
  const [fontsLoaded] = useFonts({
    "pretendard-thin": require("./assets/fonts/Pretendard-Thin.ttf"),
    "pretendard-extraLight": require("./assets/fonts/Pretendard-ExtraLight.ttf"),
    "pretendard-light": require("./assets/fonts/Pretendard-Light.ttf"),
    "pretendard-regular": require("./assets/fonts/Pretendard-Regular.ttf"),
    "pretendard-medium": require("./assets/fonts/Pretendard-Medium.ttf"),
    "pretendard-semiBold": require("./assets/fonts/Pretendard-SemiBold.ttf"),
    "pretendard-bold": require("./assets/fonts/Pretendard-Bold.ttf"),
    "pretendard-extraBold": require("./assets/fonts/Pretendard-ExtraBold.ttf"),
    "pretendard-black": require("./assets/fonts/Pretendard-Black.ttf"),
  });
  const statusBarStyle = "light";
  const statusBarColor = Colors.bgOrg;

  // const requestUserPermission = async () => {
  //   const authStatus = await messaging().requestPermission();
  //   const enabled =
  //     authStatus === messaging.AuthorizationStatus.AUTHORIZED ||
  //     authStatus === messaging.AuthorizationStatus.PROVISIONAL;

  //   if (enabled) {
  //     return getToken();
  //   }
  // };

  // const getToken = async () => {
  //   const fcmToken = await messaging().getToken();
  //   console.log("디바이스 토큰값");
  //   console.log(fcmToken);
  //   dispatch(set_deviceToken(fcmToken));
  // };
  // useEffect(() => {
  //   const unsubscribe = messaging().onMessage(async (remoteMessage) => {
  //     Alert.alert("A new FCM message arrived!", JSON.stringify(remoteMessage));
  //   });

  //   return unsubscribe;
  // }, []);
  // useEffect(() => {
  //   const unsubscribe = messaging().onMessage(async (remoteMessage) => {
  //     console.log(remoteMessage);
  //   });
  //   requestUserPermission();

  //   return unsubscribe;
  // }, []);

  return (
    <SafeAreaProvider>
      <Provider store={store}>
        <WagmiConfig config={wagmiConfig}>
          {/* <WagmiConfig config={config}> */}
          <NavigationContainer>
            {/* <StatusBar style="auto" /> */}
            {/* <StatusBar style={styles.statusBar} /> */}
            <StatusBar
              backgroundColor={statusBarColor}
              style={statusBarStyle}
            />
            {fontsLoaded && (
              <SafeAreaView style={styles.safeAreaView}>
                <AuthRouters />
              </SafeAreaView>
            )}
            <Web3Modal />
          </NavigationContainer>
        </WagmiConfig>
      </Provider>
    </SafeAreaProvider>
  );
}

const styles = StyleSheet.create({
  safeAreaView: {
    flex: 1,
    paddingTop: Constants.statusBarHeight,
  },
});
