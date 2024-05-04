import { createStackNavigator } from "@react-navigation/stack";
import SettingScreen from "../screens/setting/SettingScreen";

const SettingRouters = () => {
  const SettingStack = createStackNavigator();

  return (
    <SettingStack.Navigator screenOptions={{ headerShown: false }}>
      <SettingStack.Screen name="setting" component={SettingScreen} />
    </SettingStack.Navigator>
  )
}

export default SettingRouters;