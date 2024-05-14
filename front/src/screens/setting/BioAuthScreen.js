import { View, StyleSheet } from 'react-native';
import Colors from '../../constants/Colors';
import SettingService from '../../utils/setting';
import * as LocalAuthentication from 'expo-local-authentication';
import { useFocusEffect } from '@react-navigation/native';
import { useCallback } from 'react';

const BioAuthScreen = ({navigation, route}) => {
  const destination = route.params.destination;
  const subDestination = route.params.subDestination;
  const cancelDestination = route.params.cancelDestination;
  const cancelSubDestination = route.params.cancelSubDestination;
  

  useFocusEffect(
    useCallback(() => {
      const proceedBioAuth = async () => {
        const isEnabled = await SettingService.getBiometricEnabled();
        
        if (isEnabled) {
          const bioAuth = await LocalAuthentication.authenticateAsync({
            promptMessage: '생체 인증 진행',
            fallbackLabel: '비밀번호 입력',
            disableDeviceFallback: false
          });
  
          if (bioAuth.success) {
            console.log('생체 인증 성공');
            navigation.navigate(destination, {
              screen: subDestination
            });
          } else if (bioAuth.error === 'user_cancel') {
            navigation.navigate(cancelDestination, {
              screen: cancelSubDestination
            });
          }
        } else {
          navigation.navigate('pinAuth', {
            destination,
            subDestination,
            cancelDestination,
            cancelSubDestination
          });
        }
      };
  
      proceedBioAuth();
    }, [navigation])
  );

  return (
    <View style={styles.container}>
    </View>
  )
}

export default BioAuthScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: Colors.bgOrg
  }
});