import { createStackNavigator } from "@react-navigation/stack";
import HomeScreen from "../screens/home/HomeScreen";
import AnnouncementScreen from "../screens/home/AnnouncementScreen";
import NotificationScreen from "../screens/home/NotificationScreen";
import NoticeScreen from "../screens/home/NoticeScreen";

const HomeRouters = () => {
  const HomeStack = createStackNavigator();

  return (
    <HomeStack.Navigator screenOptions={{ headerShown: false }}>
      <HomeStack.Screen name="home" component={HomeScreen} />
      <HomeStack.Screen name="announcement" component={AnnouncementScreen} />
      <HomeStack.Screen name="notice" component={NoticeScreen} />
      <HomeStack.Screen name="notification" component={NotificationScreen} />
    </HomeStack.Navigator>
  );
};

export default HomeRouters;
