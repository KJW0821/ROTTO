import { createStackNavigator } from "@react-navigation/stack";
import MyScreen from "../screens/my/MyScreen";
import AccountScreen from "../screens/my/AccountScreen";
import ConnectionScreen from "../screens/my/ConnectionScreen";

const MyRouters = () => {
  const MyStack = createStackNavigator();

  return (
    <MyStack.Navigator screenOptions={{ headerShown: false }}>
      <MyStack.Screen name="mypage" component={MyScreen} />
      <MyStack.Screen name="account" component={AccountScreen} />
      <MyStack.Screen name="connection" component={ConnectionScreen} />
    </MyStack.Navigator>
  );
};

export default MyRouters;
