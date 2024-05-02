import { createMaterialBottomTabNavigator } from "@react-navigation/material-bottom-tabs";
import HomeRouters from "./HomeRouters";

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
    </Tab.Navigator>
  )
}

export default Routers;