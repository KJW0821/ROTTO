import { createMaterialBottomTabNavigator } from "@react-navigation/material-bottom-tabs";
import HomeRouters from "./HomeRouters";
import SettingRouters from "./SettingRouters";
import DiscoveryRouters from "./DiscoveryRouters";
import { Ionicons } from "@expo/vector-icons";

const Routers = () => {
  const Tab = createMaterialBottomTabNavigator();

  return (
    <Tab.Navigator
      barStyle={{ backgroundColor: 'white' }}
      initialRouteName="HomePage"
    >
      <Tab.Screen
        name="홈"
        component={HomeRouters}
        options={{
          tabBarIcon: () => (
            <Ionicons name="home-outline" size={24} color="black" />
          ),
        }}
      />
      <Tab.Screen name="발견" component={DiscoveryRouters} />
      <Tab.Screen name="설정" component={SettingRouters} />
    </Tab.Navigator>
  );
};

export default Routers;
