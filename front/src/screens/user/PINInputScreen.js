import { View, Text, SafeAreaView, StyleSheet, Alert } from 'react-native';
import UserTopBar from '../../components/user/UserTopBar';
import Title from '../../components/user/Title';
import ReactNativePinView from 'react-native-pin-view';
import { useEffect, useState, useRef } from 'react';
import KeyService from '../../utils/pinCode';
import { Ionicons } from '@expo/vector-icons';
import Colors from '../../constants/Colors';

const PINInputScreen = ({navigation}) => {
  const pinView = useRef(null)
  const [showRemoveButton, setShowRemoveButton] = useState(false)
  const [showCompletedButton, setShowCompletedButton] = useState(false)
  const [enteredPin, setEnteredPin] = useState("")

  useEffect(() => {
    if (enteredPin.length > 0) {
      setShowRemoveButton(true)
    } else {
      setShowRemoveButton(false)
    }
    if (enteredPin.length === 6) {
      setShowCompletedButton(true)
    } else {
      setShowCompletedButton(false)
    }
  }, [enteredPin]);

  const resetPinCode = () => {
    setEnteredPin('');
    pinView.current.clearAll();
  };

  const checkPinCode = async () => {
    const storedPin = await KeyService.getPinCode();
    if (storedPin === enteredPin) {
      navigation.navigate('Routers');
    } else {
      Alert.alert('잘못 입력하셨습니다. 다시 입력해주세요.', '', [{text: '재입력', onPress: resetPinCode}])
      setEnteredPin('');
      pinView.current.clearAll();
    } 
  }

  return (
    <SafeAreaView style={styles.safeAreaView}>
      <UserTopBar navigation={navigation} destination= 'SignIn' />
      <View style={styles.container}>
        <Title>간편 로그인</Title>
        <View style={styles.pinContainer}>
          <Text style={styles.description}>간편 비밀번호를 입력해주세요</Text>
          <ReactNativePinView
            inputSize={20}
            ref={pinView}
            pinLength={6}
            buttonSize={60}
            onValueChange={value => setEnteredPin(value)}
            inputAreaStyle={{
              marginBottom: '25%',
            }}
            inputViewEmptyStyle={{
              backgroundColor: '#D9D9D9',
            }}
            inputViewFilledStyle={{
              backgroundColor: Colors.btnBlue,
            }}
            buttonViewStyle={{
              borderWidth: 1,
              borderColor: "black",
            }}
            buttonTextStyle={{
              color: "black"
            }}
            onButtonPress={key => {
              if (key === "custom_left") {
                pinView.current.clear()
              }
              if (key === "custom_right") {
                checkPinCode();
              }
            }}
            customLeftButton={showRemoveButton ? <Ionicons name={"arrow-back-outline"} size={36} color={"black"} /> : undefined}
            customRightButton={showCompletedButton ? <Ionicons name={"checkmark-outline"} size={36} color={"black"} /> : undefined}
          />
        </View>
      </View>
      
    </SafeAreaView>
  )
}

export default PINInputScreen;

const styles = StyleSheet.create({
  safeAreaView: {
    flex: 1,
    backgroundColor: 'white',
    alignItems: 'center'
  },
  container: {
    width: '80%',
    flex: 1
  },
  pinContainer: {
    width: '100%',
    flex: 1,
    justifyContent: 'center'
  },
  description: {
    fontFamily: 'pretendard-semiBold',
    fontSize: 16,
    textAlign: 'center',
    marginBottom: 32
  }
});