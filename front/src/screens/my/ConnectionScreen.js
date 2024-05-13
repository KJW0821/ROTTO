import { View, Text, StyleSheet } from 'react-native';
import DetailTopBar from '../../components/common/DetailTopBar';

const ConnectionScreen = ({navigation}) => {
  return (
    <View style={styles.container}>
      <DetailTopBar title='계좌 연결' navigation={navigation} destination='account' />
    </View>
  )
}

export default ConnectionScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    backgroundColor: 'white',
  }
});