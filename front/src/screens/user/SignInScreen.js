import { View, TextInput, Text, StyleSheet } from 'react-native';
import { MaterialIcons } from '@expo/vector-icons';
import Title from '../../components/user/Title';
import CustomButton from '../../components/common/CustomButton';
import Colors from '../../constants/Colors';
import { useState } from 'react';
import UserTopBar from '../../components/user/UserTopBar';
import { signIn } from '../../utils/userApi';

const SignInScreen = ({navigation}) => {
  const [phoneNumber, setPhoneNumber] = useState('');
  const [password, setPassword] = useState('');
  const [errMsg, setErrMsg] = useState('');

  const phoneNumberInputHandler = (enteredText) => {
    setPhoneNumber(enteredText);
  };

  const passwordInputHandler = (enteredText) => {
    setPassword(enteredText);
  };

  const signInHandler = async () => {
    const res = await signIn({
      phoneNum: phoneNumber,
      password
    });
    if (res.status === 200) {
      navigation.navigate('Routers');
      setErrMsg('');
    } else {
      setErrMsg('전화번호와 비밀번호를 다시 확인해주세요');
    }
  };

  return (
    <View style={styles.container}>
      <UserTopBar navigation={navigation} />
      <View style={styles.dataContainer}>
        <Title>로그인</Title>
        <View style={styles.inputsContainer}>
          <View>
            <Text style={styles.title}>전화번호</Text>
            <View style={[styles.inputContainer , { marginBottom: 8 }]}>
              <TextInput 
                style={[styles.inputText, {flex: 1}]} 
                autoCorrect={false}
                autoCapitalize="none"
                underlineColorAndroid="transparent"
                keyboardType="number-pad"
                onChangeText={phoneNumberInputHandler}
                value={phoneNumber
                  .replace(/[^0-9]/g, '')
                  .replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3")
                }
              />
              {
                phoneNumber.length ?
                <MaterialIcons name="cancel" size={18} color={Colors.iconGray} onPress={() => setPhoneNumber('')} /> : <></>
              }
            </View>
            <Text style={[styles.title, { marginTop: 8 }]}>비밀번호</Text>
            <View style={[styles.inputContainer, { marginBottom: 16 }]}>
              <TextInput 
                style={[styles.inputText, {flex: 1}]} 
                autoCorrect={false}
                autoCapitalize="none"
                underlineColorAndroid="transparent"
                onChangeText={passwordInputHandler}
                value={password}
                secureTextEntry={true}
                maxLength={20}
              />
              {
                password.length ?
                <MaterialIcons name="cancel" size={18} color={Colors.iconGray} onPress={() => setPassword('')} /> : <></>
              }
            </View>
            { errMsg && <Text style={styles.warnText}>{errMsg}</Text>}
          </View>
        </View>
        <CustomButton onPress={signInHandler} disabled={!phoneNumber || !password}>로그인</CustomButton>
      </View>
    </View>
  )
}

export default SignInScreen;

const styles = StyleSheet.create({
  container: {
    backgroundColor: 'white',
    flex: 1,
    alignItems: 'center'
  },
  dataContainer: {
    width: '80%'
  },
  inputsContainer: {
    gap: 40,
    marginBottom: 44,
    marginTop: 32
  },
  inputText: {
    fontFamily: 'pretendard-regular',
    fontSize: 14
  },
  title: {
    fontFamily: 'pretendard-regular',
    fontSize: 12,
    color: Colors.fontGray,
    marginBottom: 8
  },
  inputContainer: {
    width: '100%',
    borderBottomColor: Colors.fontGray,
    borderBottomWidth: 1,
    alignItems: 'center',
    flexDirection: 'row',
    height: 28
  },
  warnText: {
    color: 'red',
    fontSize: 12
  }
});