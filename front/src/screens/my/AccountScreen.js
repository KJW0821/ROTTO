import { View, Text, StyleSheet } from 'react-native';
import DetailTopBar from '../../components/common/DetailTopBar';
import MyAccount from '../../components/my/MyAccount';
import ConnectedAccount from '../../components/my/ConnectedAccount';

const AccountScreen = ({navigation}) => {
  return (
    <View style={styles.container}>
      <DetailTopBar title='계좌 상세' navigation={navigation} />
      <View style={styles.innerContainer}>
        <MyAccount detail={true} />
        <ConnectedAccount navigation={navigation} />
      </View>
    </View>
  )
}

export default AccountScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    backgroundColor: 'white',
  },
  innerContainer: {
    width: '100%',
    gap: 16,
    alignItems: 'center'
  }
});