import { createStackNavigator } from "@react-navigation/stack";
import SettingScreen from "../screens/setting/SettingScreen";
import InquiryListScreen from './../screens/setting/InquiryListScreen';
import InquiryCreateScreen from "../screens/setting/InquiryCreateScreen";
import PINChangeScreen from "../screens/setting/PINChangeScreen";
import InquiryDetailScreen from "../screens/setting/InquiryDetailScreen";
import TermsScreen from "../screens/setting/TermsScreen";

const SettingRouters = () => {
  const SettingStack = createStackNavigator();

  return (
    <SettingStack.Navigator screenOptions={{ headerShown: false }}>
      <SettingStack.Screen name="setting" component={SettingScreen} />
      <SettingStack.Screen name="inquiry" component={InquiryListScreen} />
      <SettingStack.Screen name="inquiryCreate" component={InquiryCreateScreen} />
      <SettingStack.Screen name="inquiryDetail" component={InquiryDetailScreen} />
      <SettingStack.Screen name="pinChange" component={PINChangeScreen} />
      <SettingStack.Screen name="terms" component={TermsScreen} />
    </SettingStack.Navigator>
  )
}

export default SettingRouters;