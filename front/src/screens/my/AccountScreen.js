import { View, Text, StyleSheet } from 'react-native';
import DetailTopBar from '../../components/common/DetailTopBar';
import MyAccount from '../../components/my/MyAccount';

const AccountScreen = ({navigation}) => {
  return (
    <View style={styles.container}>
      <DetailTopBar title='계좌 상세' navigation={navigation} />
      <MyAccount detail={true} />
    </View>
  )
}

export default AccountScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    backgroundColor: 'white'
  }
});