import { createStackNavigator } from "@react-navigation/stack";
import MyScreen from "../screens/my/MyScreen";

const MyRouters = () => {
  const MyStack = createStackNavigator();

  return (
    <MyStack.Navigator screenOptions={{ headerShown: false }}>
      <MyStack.Screen name="MyPage" component={MyScreen} />
    </MyStack.Navigator>
  );
};

export default MyRouters;
