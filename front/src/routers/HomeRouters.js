import { createStackNavigator } from "@react-navigation/stack";
import HomeScreen from "../screens/HomeScreen";

const HomeRouters = () => {
  const HomeStack = createStackNavigator();

  return (
    <HomeStack.Navigator>
      <HomeStack.Screen name="home" component={HomeScreen} />
    </HomeStack.Navigator>
  )
}

export default HomeRouters;