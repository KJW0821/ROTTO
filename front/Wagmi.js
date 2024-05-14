// import { StatusBar } from "expo-status-bar";
// import { StyleSheet, SafeAreaView } from "react-native";
// import { NavigationContainer } from "@react-navigation/native";
// import { store } from "./src/stores/Store";
// import { Provider } from "react-redux";
// import { SafeAreaProvider } from "react-native-safe-area-context";
// import AuthRouters from "./src/routers/AuthRouters";
// import Constants from "expo-constants";
// import { useFonts } from "expo-font";
// import Colors from "./src/constants/Colors";
// import { GestureHandlerRootView } from "react-native-gesture-handler";
// import BottomSheet from "./src/components/common/MyBottomSheet";
// import '@walletconnect/react-native-compat'
// import { WagmiConfig } from 'wagmi'
// import { mainnet, polygon, arbitrum } from 'viem/chains'
// import { Web3Modal, createWeb3Modal, defaultWagmiConfig } from '@web3modal/wagmi-react-native';
// import { defineChain } from "viem";

// const projectId = '41c800331b3143bdaddeef0fdefb7852';

// const metadata = {
//   name: 'rotto',
//   description: '커피 STO 투자 증권 앱',
//   url: 'exp://192.168.30.203:8081',
//   icons: ['../../../assets/images/skyIcon.png'],
//   redirect: {
//     native: 'exp://192.168.30.203:8081',
//   }
// };

// const ssafy = defineChain({
//   id: 31221,
//   name: 'ssafy',
//   nativeCurrency: { name: 'Ether', symbol: 'ETH', decimals: 18 },
//   rpcUrls: {
//     default: { http: ['https://rpc.ssafy-blockchain.com'] },
//   },
//   testnet: false
// });

// const chains = [mainnet, polygon, arbitrum];

// const wagmiConfig = defaultWagmiConfig({ chains, projectId, metadata });

// createWeb3Modal({
//   projectId,
//   chains,
//   wagmiConfig,
//   enableAnalytics: true
// });

// export default function App() {
//   const [fontsLoaded] = useFonts({
//     "pretendard-thin": require("./assets/fonts/Pretendard-Thin.ttf"),
//     "pretendard-extraLight": require("./assets/fonts/Pretendard-ExtraLight.ttf"),
//     "pretendard-light": require("./assets/fonts/Pretendard-Light.ttf"),
//     "pretendard-regular": require("./assets/fonts/Pretendard-Regular.ttf"),
//     "pretendard-medium": require("./assets/fonts/Pretendard-Medium.ttf"),
//     "pretendard-semiBold": require("./assets/fonts/Pretendard-SemiBold.ttf"),
//     "pretendard-bold": require("./assets/fonts/Pretendard-Bold.ttf"),
//     "pretendard-extraBold": require("./assets/fonts/Pretendard-ExtraBold.ttf"),
//     "pretendard-black": require("./assets/fonts/Pretendard-Black.ttf"),
//   });
//   const statusBarStyle = "light";
//   const statusBarColor = Colors.bgOrg;

//   return (
//     <SafeAreaProvider>
//       <Provider store={store}>
//         <WagmiConfig config={wagmiConfig}>
//           <NavigationContainer>
//               {/* <StatusBar style="auto" /> */}
//               {/* <StatusBar style={styles.statusBar} /> */}
//               <StatusBar
//                 backgroundColor={statusBarColor}
//                 style={statusBarStyle}
//               />
//               {fontsLoaded && (
//                 <SafeAreaView style={styles.safeAreaView}>
//                   <AuthRouters />
//                 </SafeAreaView>
//               )}
//               <Web3Modal />
//           </NavigationContainer>
//         </WagmiConfig>
//       </Provider>
//     </SafeAreaProvider>
//   );
// }

// const styles = StyleSheet.create({
//   safeAreaView: {
//     flex: 1,
//     paddingTop: Constants.statusBarHeight,
//   },
// });
