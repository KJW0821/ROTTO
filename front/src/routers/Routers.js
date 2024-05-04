import { createMaterialBottomTabNavigator } from "@react-navigation/material-bottom-tabs";
import HomeRouters from "./HomeRouters";
import SettingRouters from "./SettingRouters";

const Routers = () => {
  const Tab = createMaterialBottomTabNavigator();

  return (
    <Tab.Navigator
      initialRouteName="HomePage"
    >
      <Tab.Screen 
        name="HomePage"
        component={HomeRouters} 
      />
      <Tab.Screen
        name="SettingPage"
        component={SettingRouters}
      />
    </Tab.Navigator>
  )
}

export default Routers;