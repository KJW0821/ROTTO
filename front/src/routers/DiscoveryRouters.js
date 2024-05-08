import { createStackNavigator } from "@react-navigation/stack";
import DiscoveryScreen from "../screens/discovery/DiscoveryScreen";

const DiscoveryRouters = () => {
  const DiscoveryStack = createStackNavigator();

  return (
    <DiscoveryStack.Navigator screenOptions={{ headerShown: false }}>
      <DiscoveryStack.Screen name="discovery" component={DiscoveryScreen} />
    </DiscoveryStack.Navigator>
  );
};

export default DiscoveryRouters;
