import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { store } from './src/stores/store';
import { Provider } from 'react-redux';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import AuthRouters from './src/routers/AuthRouters';

export default function App() {
  return (
    <SafeAreaProvider>
      {/* <Provider store={store}> */}
        <NavigationContainer>
          <StatusBar style="auto" />
          <AuthRouters />
        </NavigationContainer>
      {/* </Provider> */}
    </SafeAreaProvider>
  );
}

const styles = StyleSheet.create({
});
