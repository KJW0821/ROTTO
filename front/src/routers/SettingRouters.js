import { createStackNavigator } from "@react-navigation/stack";
import SettingScreen from "../screens/setting/SettingScreen";
import InquiryListScreen from './../screens/setting/InquiryListScreen';
import InquiryCreateScreen from "../screens/setting/InquiryCreateScreen";

const SettingRouters = () => {
  const SettingStack = createStackNavigator();

  return (
    <SettingStack.Navigator screenOptions={{ headerShown: false }}>
      <SettingStack.Screen name="setting" component={SettingScreen} />
      <SettingStack.Screen name="inquiry" component={InquiryListScreen} />
      <SettingStack.Screen name="inquiryCreate" component={InquiryCreateScreen} />
    </SettingStack.Navigator>
  )
}

export default SettingRouters;