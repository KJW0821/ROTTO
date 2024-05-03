import { StatusBar } from 'expo-status-bar';
import { StyleSheet, SafeAreaView } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { store } from './src/stores/store';
import { Provider } from 'react-redux';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import AuthRouters from './src/routers/AuthRouters';
import Constants from 'expo-constants';
import { useFonts } from 'expo-font';

export default function App() {
  const [fontsLoaded] = useFonts({
    'pretendard-thin': require('./assets/fonts/Pretendard-Thin.ttf'),
    'pretendard-extraLight': require('./assets/fonts/Pretendard-ExtraLight.ttf'),
    'pretendard-light': require('./assets/fonts/Pretendard-Light.ttf'),
    'pretendard-regular': require('./assets/fonts/Pretendard-Regular.ttf'),
    'pretendard-medium': require('./assets/fonts/Pretendard-Medium.ttf'),
    'pretendard-semiBold': require('./assets/fonts/Pretendard-SemiBold.ttf'),
    'pretendard-bold': require('./assets/fonts/Pretendard-Bold.ttf'),
    'pretendard-extraBold': require('./assets/fonts/Pretendard-ExtraBold.ttf'),
    'pretendard-black': require('./assets/fonts/Pretendard-Black.ttf')
  })

  return (
    <SafeAreaProvider>
      <Provider store={store}>
        <NavigationContainer>
          <StatusBar style="auto" />
          {
            fontsLoaded && 
            <SafeAreaView style={styles.cotainer}>
              <AuthRouters />
            </SafeAreaView>
          }
        </NavigationContainer>
      </Provider>
    </SafeAreaProvider>
  );
}

const styles = StyleSheet.create({
  cotainer: {
    flex: 1,
    paddingTop: Constants.statusBarHeight
  },
});
