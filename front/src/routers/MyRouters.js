import { createStackNavigator } from "@react-navigation/stack";
import MyScreen from "../screens/my/MyScreen";
import AccountScreen from "../screens/my/AccountScreen";

const MyRouters = () => {
  const MyStack = createStackNavigator();

  return (
    <MyStack.Navigator screenOptions={{ headerShown: false }}>
      <MyStack.Screen name="mypage" component={MyScreen} />
      <MyStack.Screen name="account" component={AccountScreen} />
    </MyStack.Navigator>
  );
};

export default MyRouters;
