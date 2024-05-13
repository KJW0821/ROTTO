import { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Image, Pressable } from 'react-native';
import { getAccountInfo } from '../../utils/accountApi';
import { MaterialIcons } from '@expo/vector-icons';
import CustomButton from '../common/CustomButton';
import Colors from '../../constants/Colors';

const MyAccount = ({navigation, detail}) => {
  const [fundingAccount, setFundingAccount] = useState();
  
  useEffect(() => {
    const getFundingAccount = async () => {
      const res = await getAccountInfo();
      setFundingAccount(res);
    };

    getFundingAccount();
  }, [navigation])

  return (
    <View style={[styles.container, detail && styles.border]}>
      {
        fundingAccount &&
        <>
          <View style={styles.topContainer}>
            <View style={styles.accountInfoContainer}>
              <Image style={styles.bankLogo} source={require("../../../assets/images/ssafyLogo.png")} resizeMode="stretch" />
              <Text style={styles.bankName}>싸피 은행</Text>
              <Text style={styles.accountNum}>{fundingAccount.accountNum}</Text>
            </View>
            {
              !detail &&
              <Pressable onPress={() => navigation.navigate('account')}>
                <MaterialIcons name="arrow-forward-ios" size={16} />
              </Pressable>
            }
          </View>
          <Text style={styles.balanceText}>{fundingAccount.accountBalance} 원</Text>
          <View style={[styles.buttonContainer, detail && { justifyContent: 'space-between' }]}>
            <CustomButton 
              style={{ width: detail ? '48%' : '16%', height: detail ? 30 : 24 }} 
              fontFamily='pretendard-medium'
              onPress={() => navigation.navigate('charge')}
            >
              채우기
            </CustomButton>
            <CustomButton style={{ width: detail ? '48%' : '16%', height: detail ? 30 : 24 }} fontFamily='pretendard-medium' btnColor='black'>보내기</CustomButton>
          </View>
        </>
      }
    </View>
  )
}

export default MyAccount;

const styles = StyleSheet.create({
  container: {
    width: '90%',
    backgroundColor: 'white',
    borderRadius: 10,
    padding: 18,
    gap: 16
  },
  border: {
    borderColor: Colors.fontGray,
    borderWidth: 0.5
  },
  topContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    position: 'relative'
  },
  accountInfoContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 6
  },
  bankLogo: {
    height: 20,
    width: 20
  },
  bankName: {
    fontSize: 16,
    fontFamily: 'pretendard-semiBold'
  },
  accountNum: {
    fontSize: 12,
    fontFamily: 'pretendard-regular'
  },
  balanceText: {
    fontSize: 20,
    fontFamily: 'pretendard-regular',
    textAlign: 'center'
  },
  buttonContainer: {
    flexDirection: 'row',
    gap: 6,
    justifyContent: 'flex-end'
  }
});