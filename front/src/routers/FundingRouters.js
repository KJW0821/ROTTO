import { createStackNavigator } from "@react-navigation/stack";
import FundingScreen from "../screens/funding/FundingScreen";

const FundingRouters = () => {
  const FundingStack = createStackNavigator();

  return (
    <FundingStack.Navigator screenOptions={{ headerShown: false }}>
      <FundingStack.Screen name="funding" component={FundingScreen} />
    </FundingStack.Navigator>
  );
};

export default FundingRouters;
