import { View, Button, StyleSheet } from 'react-native';
import { useNavigation } from '@react-navigation/native';

const OnboardingScreen = () => {
  const navigation = useNavigation();

  return (
    <View>
      <Button title="회원가입" onPress={() => navigation.navigate('NameIdInput')} />
      <Button title="로그인" />
    </View>
  )
}

export default OnboardingScreen;

const styles = StyleSheet.create({
});