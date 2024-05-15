import { View, Text, ScrollView, StyleSheet } from 'react-native';
import DetailTopBar from '../../components/common/DetailTopBar';

const FundingDetailScreen = (navigation) => {
  return (
    <View style={styles.container}>
      <DetailTopBar navigation={navigation} />
      <ScrollView>
        <Text>펀딩 정보</Text>
      </ScrollView>
    </View>
  )
}

export default FundingDetailScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    backgroundColor: 'white'
  }
})