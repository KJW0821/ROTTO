import { View, Text, StyleSheet, TextInput } from 'react-native';
import DetailTopBar from '../../components/common/DetailTopBar';
import { getRealAccountInfo } from '../../utils/accountApi';
import { useDispatch, useSelector } from 'react-redux';
import { setConnectedAccount } from '../../stores/mySlice';
import { useFocusEffect } from '@react-navigation/native';
import { useCallback } from 'react';
import Colors from '../../constants/Colors';
import CustomButton from '../../components/common/CustomButton';

const ChargeScreen = ({navigation}) => {
  const dispatch = useDispatch();
  const connectedAccount = useSelector(state => state.myPageInfo.connectedAccount);

  useFocusEffect(
    useCallback(() => {
      const getRealAccountData = async () => {
        const res = await getRealAccountInfo();
        dispatch(setConnectedAccount(res));
      };

      getRealAccountData();
      console.log(connectedAccount);
    }, [])
  );
  
  return (
    <View style={styles.container}>
      <DetailTopBar title='채우기' navigation={navigation} />
      <View style={styles.innerContainer}>
        <View style={styles.inputContainer}>
          <TextInput 
            style={styles.inputText}
            placeholder='얼마나 채울까요?'
            keyboardType='number-pad'
          />
          <Text style={styles.unitText}>원</Text>
        </View>
        {
          connectedAccount &&
          <View>
            <Text style={styles.balance}>최대 가능 금액: {connectedAccount.accountBalance}원</Text>
          </View>
        }
        <CustomButton>채우기</CustomButton>
      </View>
    </View>
  )
}

export default ChargeScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    backgroundColor: 'white',
  },
  innerContainer: {
    width: '80%',
    paddingTop: 16,
    gap: 12
  },
  inputContainer: {
    width: '100%',
    borderBottomWidth: 1,
    borderBottomColor: Colors.fontGray,
    padding: 8,
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center'
  },
  inputText: {
    fontSize: 20,
    fontFamily: 'pretendard-medium',    
  },
  unitText: {
    fontSize: 20,
    fontFamily: 'pretendard-medium',
    color: Colors.fontGray
  },
  balance: {
    fontFamily: 'pretendard-medium',
    fontSize: 12
  }
});