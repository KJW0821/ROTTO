import { View, Pressable, TextInput, Text, StyleSheet } from 'react-native';
import { AntDesign, MaterialIcons } from '@expo/vector-icons';
import Title from '../../components/user/Title';
import CustomButton from '../../components/common/CustomButton';
import Colors from '../../constants/Colors';
import InputBox from '../../components/user/InputBox';
import { useEffect, useRef, useState } from 'react';

const SignupScreen = () => {
  const emptyCells = '●●●●●●●';
  const [name, setName] = useState('');
  const [personId, setPersonId] = useState('');
  const nameInputRef = useRef();
  const idInputRef = useRef();

  const setFocus = (target) => {
    target.current.focus();
  };

  const nameInputHandler = (enteredText) => {
    setName(enteredText);
  };

  const idInputHandler = (enteredText) => {
    setPersonId(enteredText);
  };

  return (
    <View style={styles.container}>
      <View style={styles.topBar}>
        <Pressable style={styles.backButton}>
          <AntDesign name="left" size={24} />
        </Pressable>
      </View>
      <View style={styles.dataContainer}>
        <Title>회원가입</Title>
        <View style={styles.inputsContainer}>
          <InputBox 
            description="이름을 입력해주세요"
            title="이름"
          >
            <Pressable style={styles.inputContainer} onPress={setFocus.bind(this, nameInputRef)}>
              <TextInput 
                style={styles.inputText} 
                autoCorrect={false}
                autoCapitalize="none"
                underlineColorAndroid="transparent"
                ref={nameInputRef}
                onChangeText={nameInputHandler}
                value={name}
              />
            </Pressable>
          </InputBox>
          <InputBox 
            description="주민등록번호를 입력해주세요"
            title="주민등록번호"
          >
            <Pressable onPress={setFocus.bind(this, idInputRef)} style={styles.inputContainer}>
              <TextInput 
                style={[styles.inputText, {width: personId.length ? 'auto' : '0.5%'}]} 
                autoCorrect={false}
                autoCapitalize="none"
                underlineColorAndroid="transparent"
                maxLength={7}
                keyboardType="number-pad"
                ref={idInputRef}
                onChangeText={idInputHandler}
                value={personId > 6 ? personId.slice(0, 7) + '-' + personId.slice(-1) : personId}
              />
              <Text style={styles.emptyCellText}>
                {
                  personId.length !== 7 &&
                  emptyCells.slice(personId.length, -1) + '-' + emptyCells.slice(-1)
                }
              </Text>
              <Text style={styles.inputText}>●●●●●●</Text>
            </Pressable>
          </InputBox>
        </View>
        <CustomButton>다음</CustomButton>
      </View>
    </View>
  )
}

export default SignupScreen;

const styles = StyleSheet.create({
  container: {
    backgroundColor: 'white',
    flex: 1,
    alignItems: 'center'
  },
  topBar: {
    width: '100%',
    backgroundColor: 'white'
  },
  backButton: {
    marginLeft: 24,
    marginVertical: 16
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
  },
  inputContainer: {
    flexDirection: 'row',
    flex: 1,
    alignItems: 'center'
  }
});