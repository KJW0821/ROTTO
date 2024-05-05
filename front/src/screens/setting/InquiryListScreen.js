import { ScrollView, View, StyleSheet } from 'react-native'; 
import SettingTopBar from '../../components/setting/SettingTopBar';
import CustomButton from '../../components/common/CustomButton';
import InquiryCard from '../../components/setting/InquiryCard';

const InquiryListScreen = ({navigation}) => {
  const inquiryList = [
    {
      reqBoardCode: 0,
      title: '으아아앙아악 살려줘요'
    },
    {
      reqBoardCode: 1,
      title: '이것은 문의 제목입니다... 어떤 문의일까'
    },
    {
      reqBoardCode: 2,
      title: '이것은 문의 제목입니다... 어떤 문의일까'
    },
    {
      reqBoardCode: 3,
      title: '이것은 문의 제목입니다... 어떤 문의일까 제목이 엄청 길면 어떻게 될까 실험실험 테스트테스트'
    }
  ]

  return (
    <View style={styles.container}>
      <SettingTopBar navigation={navigation} title="문의 내역" />
      <View style={styles.listContainer}>
        <View style={styles.buttonContainer}>
          <CustomButton 
            style={{width: '20%', height: 23 }} 
            fontFamily='pretendard-medium'
            onPress={() => navigation.navigate('inquiryCreate')}
          >
            문의 작성
          </CustomButton>
        </View>
        <ScrollView contentContainerStyle={styles.scrollContainer}>
          {
            inquiryList.map(el => <InquiryCard key={el.reqBoardCode} data={el} />)
          }
        </ScrollView>
      </View>
    </View>
  )
}

export default InquiryListScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
    alignItems: 'center'
  },
  listContainer: {
    width: '82%',
    gap: 16
  },
  buttonContainer: {
    width: '100%',
    alignItems: 'flex-end'
  },
  scrollContainer: {
    rowGap: 12
  }
});