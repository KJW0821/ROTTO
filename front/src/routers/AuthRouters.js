import { createStackNavigator } from "@react-navigation/stack";
import Routers from "./Routers";

const AuthRouters = () => {
  const AuthStack = createStackNavigator();

  return (
    <AuthStack.Navigator>
      <AuthStack.Screen name="Routers" component={Routers} />
    </AuthStack.Navigator>
  )
}

export default AuthRouters;