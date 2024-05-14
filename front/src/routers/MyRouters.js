import { createStackNavigator } from "@react-navigation/stack";
import MyScreen from "../screens/my/MyScreen";
import AccountScreen from "../screens/my/AccountScreen";
import ConnectionScreen from "../screens/my/ConnectionScreen";
import ChargeScreen from "../screens/my/ChargeScreen";
import BioAuthScreen from "../screens/my/BioAuthScreen";
import PinAuthScreen from "../screens/my/PinAuthScreen";
import TransactionResultScreen from "../screens/my/TransactionResultScreen";

const MyRouters = () => {
  const MyStack = createStackNavigator();

  return (
    <MyStack.Navigator screenOptions={{ headerShown: false }}>
      <MyStack.Screen name="mypage" component={MyScreen} />
      <MyStack.Screen name="account" component={AccountScreen} />
      <MyStack.Screen name="connection" component={ConnectionScreen} />
      <MyStack.Screen name="charge" component={ChargeScreen} />
      <MyStack.Screen name="transactionResult" component={TransactionResultScreen} />
      <MyStack.Screen name="transactionBioAuth" component={BioAuthScreen} />
      <MyStack.Screen name="transactionPinAuth" component={PinAuthScreen} />
    </MyStack.Navigator>
  );
};

export default MyRouters;
