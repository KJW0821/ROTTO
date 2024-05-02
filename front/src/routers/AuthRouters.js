import { createStackNavigator } from "@react-navigation/stack";
import Routers from "./Routers";
import OnboardingScreen from "../screens/loading/OnboardingScreen";
import SignupScreen from "../screens/user/SignupScreen";

const AuthRouters = () => {
  const AuthStack = createStackNavigator();

  return (
    <AuthStack.Navigator screenOptions={{ headerShown: false }} >
      {/* <AuthStack.Screen name="Onboarding" component={OnboardingScreen} /> */}
      {/* <AuthStack.Screen name="Signup" component={SignupScreen} /> */}
      <AuthStack.Screen name="Routers" component={Routers} />
    </AuthStack.Navigator>
  )
}

export default AuthRouters;