import { View, TextInput, StyleSheet } from 'react-native';
import { MaterialIcons } from '@expo/vector-icons';
import Title from '../../components/user/Title';
import CustomButton from '../../components/common/CustomButton';
import Colors from '../../constants/Colors';
import InputBox from '../../components/user/InputBox';
import { useState } from 'react';
import UserTopBar from '../../components/user/UserTopBar';
import { useDispatch } from 'react-redux';
import { inputPhoneNumber } from '../../stores/signUpSlice';

const PhoneNumberInputScreen = ({navigation}) => {
  const [phoneNumber, setPhoneNumber] = useState('');
  const [isChecked, setIsChecked] = useState(true);
  const dispatch = useDispatch();

  const phoneNumberInputHandler = (enteredText) => {
    setPhoneNumber(enteredText);
  };

  const pressNextHandler = () => {
    dispatch(inputPhoneNumber(phoneNumber));
    navigation.navigate('PasswordInput');
  };

  return (
    <View style={styles.container}>
      <UserTopBar navigation={navigation} />
      <View style={styles.dataContainer}>
        <Title>회원가입</Title>
        <View style={styles.inputsContainer}>
          <InputBox 
            description="전화번호를 입력해주세요"
            title="전화번호"
          >
            <TextInput 
              style={[styles.inputText, {flex: 1}]} 
              autoCorrect={false}
              underlineColorAndroid="transparent"
              onChangeText={phoneNumberInputHandler}
              keyboardType="number-pad"
              value={phoneNumber
                .replace(/[^0-9]/g, '')
                .replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3")
              }
            />
            {
              phoneNumber.length ?
              <MaterialIcons style={{ marginRight: 4 }} name="cancel" size={18} color={Colors.iconGray} onPress={() => setPhoneNumber('')} /> : <></>
            }
            <CustomButton style={{ backgroundColor: 'black', width: '16%', height: 22 }} btnColor='black'>중복 확인</CustomButton>
          </InputBox>
        </View>
        <CustomButton onPress={pressNextHandler} disabled={!isChecked}>다음</CustomButton>
      </View>
    </View>
  )
}

export default PhoneNumberInputScreen;

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
  emptyCellText: {
    fontFamily: 'pretendard-regular',
    fontSize: 14,
    color: Colors.iconGray
  }
});