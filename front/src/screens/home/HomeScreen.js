import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  Button,
  Platform,
  Image,
} from "react-native";

import MyHeader from "../../components/common/MyHeader";
import MyBanner from "../../components/home/MyBanner";
import MyDeposit from "../../components/home/MyDeposit";
import FaqBanner from "../../components/home/FaqBanner";
import FarmILike from "../../components/home/FarmILike";
import FarmNews from "../../components/home/FarmNews";

import * as Notifications from "expo-notifications";
import { createRef, useEffect, useState } from "react";

const HomeScreen = ({ navigation }) => {
  Notifications.setNotificationHandler({
    handleNotification: async () => ({
      shouldShowAlert: true,
      shouldPlaySound: true,
      shouldSetBadge: false,
    }),
  });

  const responseListener = createRef();
  const notificationListener = createRef();
  const [state, setState] = useState({
    pushToken: "",
    notification: false,
  });

  const schedulePushNotification = async (data) => {
    console.log("pushToken", state.pushToken);
    await Notifications.scheduleNotificationAsync({
      content: {
        title: "테스트 알림",
        body: data,
      },
      trigger: null,
    });
  };

  // useEffect(() => {
  //   async function fetchData() {
  //     if (Platform.OS === "android") {
  //       await Notifications.setNotificationChannelAsync("default", {
  //         name: "default",
  //         importance: Notifications.AndroidImportance.MAX,
  //         vibrationPattern: [0, 250, 250, 250],
  //         lightColor: "#FF231F7C",
  //       });
  //     }
  //     const { granted } = await Notifications.getPermissionsAsync();
  //     console.log("granted", granted);
  //     if (granted) {
  //       const { data } = await Notifications.getExpoPushTokenAsync();
  //       setState({ pushToken: data });
  //       console.log("data", data);
  //     } else if (!granted) {
  //       alert("알림이 거부 되었습니다.");
  //     } else {
  //       alert("알림이 지원 되지않습니다.");
  //     }
  //     Notifications.addNotificationReceivedListener((notification) => {
  //       console.log("NOTIFICATION1:", notification);
  //     });
  //     notificationListener.current =
  //       Notifications.addNotificationReceivedListener((notification) => {
  //         setState({ notification: notification });
  //       });
  //     responseListener.current =
  //       Notifications.addNotificationResponseReceivedListener((response) => {
  //         console.log("responseRecieved", response);
  //       });
  //   }
  //   fetchData();
  // }, []);

  return (
    <MyHeader>
      {/* <View>
        <Button
          title="Show Push Token"
          onPress={async () => {
            await schedulePushNotification(state.pushToken);
          }}
        />
      </View> */}
      <ScrollView style={styles.container}>
      <FaqBanner/>
      <MyDeposit />
        <MyBanner />
        <View style={styles.infoContainer}>
          <FarmILike/>
          <FarmNews />
        </View>
      </ScrollView>
    </MyHeader>
  );
};

export default HomeScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  infoContainer: {
    flex: 1,
    marginTop: 25,
    padding: 15,
    borderTopRightRadius: 15,
    borderTopLeftRadius: 15,
    backgroundColor: "white",
    height: 1000,
  },
});
