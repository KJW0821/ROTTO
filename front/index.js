import { registerRootComponent } from "expo";
import { AppRegistry } from "react-native";
// import messaging from "@react-native-firebase/messaging";
// import { name as appName } from './app.json'
import App from "./App";

// import {PermissionsAndroid} from 'react-native';
// PermissionsAndroid.request(PermissionsAndroid.PERMISSIONS.POST_NOTIFICATIONS);

// console.log("1")
// messaging().setBackgroundMessageHandler(async (msg) => {
//     console.log("2")
//     console.log(msg);
// });
// registerRootComponent calls AppRegistry.registerComponent('main', () => App);
// It also ensures that whether you load the app in Expo Go or in a native build,
// the environment is set up appropriately
// console.log("3")
registerRootComponent(App);
// console.log("4")
// AppRegistry.registerComponent(appName, () => App)
