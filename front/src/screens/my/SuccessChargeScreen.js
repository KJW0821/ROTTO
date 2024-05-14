import { View, Text, StyleSheet, Alert } from 'react-native';
import DetailTopBar from '../../components/common/DetailTopBar';
import { useEffect, useState } from 'react';
import { chargeAccount as charge } from '../../utils/accountApi';

const SuccessChargeScreen = ({navigation, route}) => {
  const transactionBalance = route.params.amount;
  
  const [isLoading, setIsLoading] = useState(false);
  const [isSuccess, setIsSuccess] = useState(false);

  useEffect(() => {
    const chargeAccount = async () => {
      const res = await charge({ transactionBalance });
      if (res.status !== 200) {
        setIsLoading(true);
        return Alert.alert('거래가 실패했습니다.', '', [{
          text: '돌아가기',
          onPress: () => navigation.navigate('mypage')
        }])
      } else {
        setIsLoading(true);
        setIsSuccess(true);
      }
    };

    chargeAccount();
  }, [navigation])

  return (
    <View style={styles.container}>
      {
        isLoading ?
        isSuccess && (
          <>
            <DetailTopBar destination="mypage" navigation={navigation} />
            <Text>{transactionBalance}원 채우기 성공!!</Text>
          </>
        )
        :
        <Text>거래 진행 중...</Text>
      }
    </View>
  )
}

export default SuccessChargeScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
    alignItems: 'center'
  }
})