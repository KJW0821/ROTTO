import { createStackNavigator } from "@react-navigation/stack";
import HomeScreen from "../screens/home/HomeScreen";
import AnnouncementScreen from "../screens/home/AnnouncementScreen";
import NoticeScreen from "../screens/home/NoticeScreen";
import AlertScreen from "../screens/home/AlertScreen";
import AlertListScreen from "../screens/home/AlertListScreen";

const HomeRouters = () => {
  const HomeStack = createStackNavigator();

  return (
    <HomeStack.Navigator screenOptions={{ headerShown: false }}>
      <HomeStack.Screen name="home" component={HomeScreen} />
      <HomeStack.Screen name="announcement" component={AnnouncementScreen} />
      <HomeStack.Screen name="notice" component={NoticeScreen} />
      <HomeStack.Screen name="alertList" component={AlertListScreen} />
      <HomeStack.Screen name="alert" component={AlertScreen} />
    </HomeStack.Navigator>
  );
};

export default HomeRouters;
