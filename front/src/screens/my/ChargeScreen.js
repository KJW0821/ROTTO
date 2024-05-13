import { View, Text, StyleSheet } from 'react-native';
import DetailTopBar from '../../components/common/DetailTopBar';
import { getRealAccountInfo } from '../../utils/accountApi';
import { useDispatch, useSelector } from 'react-redux';
import { setConnectedAccount } from '../../stores/mySlice';
import { useFocusEffect } from '@react-navigation/native';
import { useCallback } from 'react';

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

    </View>
  )
}

export default ChargeScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    backgroundColor: 'white',
  }
});