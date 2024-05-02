import { View, Pressable, TextInput, Text, StyleSheet } from 'react-native';
import { AntDesign, MaterialIcons } from '@expo/vector-icons';
import Title from '../../components/user/Title';
import CustomButton from '../../components/common/CustomButton';
import Colors from '../../constants/Colors';
import InputBox from '../../components/user/InputBox';

const SignupScreen = () => {
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
          />
          <InputBox 
            description="주민등록번호를 입력해주세요"
            title="주민등록번호"
          />
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
  }
});