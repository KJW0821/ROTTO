import { View, Text, FlatList, StyleSheet, TouchableWithoutFeedback } from 'react-native';
import Colors from '../../constants/Colors';

const ApplyList = () => {
  const data = [
    {
      tradeCode: 1,
      farmName: '농부 주원의 에티오피아 농장',
      confirmPrice: 1000000,
      startedTime: '2024-04-25',
      endTime: '2024-05-01',
      tradeNum: null,
      totalToken: 100,
      appliedToken: 140,
      refundDate: null,
      state: 0
    },
    {
      tradeCode: 2,
      farmName: '농부 다민의 에티오피아 농장',
      confirmPrice: 1000000,
      startedTime: '2024-04-22',
      endTime: '2024-05-01',
      tradeNum: null,
      totalToken: 100,
      appliedToken: 140,
      refundDate: null,
      state: 0
    },
    {
      tradeCode: 3,
      farmName: '농부 준형의 에티오피아 농장',
      confirmPrice: 1000000,
      startedTime: '2024-04-21',
      endTime: '2024-05-01',
      tradeNum: 2,
      totalToken: 100,
      appliedToken: 140,
      refundDate: '2024-05-02',
      state: 1
    },
    {
      tradeCode: 4,
      farmName: '농부 유정의 에티오피아 농장',
      confirmPrice: 1000000,
      startedTime: '2024-04-20',
      endTime: '2024-05-01',
      tradeNum: 10,
      totalToken: 100,
      appliedToken: 140,
      refundDate: null,
      state: 1
    },
    {
      tradeCode: 5,
      farmName: '농부 세훈의 에티오피아 농장',
      confirmPrice: 1000000,
      startedTime: '2024-04-18',
      endTime: '2024-05-01',
      tradeNum: 0,
      totalToken: 100,
      appliedToken: 140,
      refundDate: '2024-05-02',
      state: 1
    },
    {
      tradeCode: 6,
      farmName: '농부 형욱의 에티오피아 농장',
      confirmPrice: 1000000,
      startedTime: '2024-04-17',
      endTime: '2024-05-01',
      tradeNum: 10,
      totalToken: 100,
      appliedToken: 140,
      refundDate: null,
      state: 1
    }
  ];

  return(
    <FlatList 
      data={data}
      renderItem={itemData => {
        return (
          <TouchableWithoutFeedback>
            <View style={styles.cardContainer}>
              <View style={styles.topContainer}>
                <Text style={styles.farmName}>{itemData.item.farmName}</Text>
                <Text style={styles.menu}>{itemData.item.startedTime} - {itemData.item.endTime}</Text>
              </View>
              <View style={styles.midContainer}>
                <View style={styles.contentContainer}>
                  <Text style={styles.menu}>신청률</Text>
                  <Text style={styles.content}>{itemData.item.appliedToken} / {itemData.item.totalToken} ROT ({Math.floor(itemData.item.appliedToken / itemData.item.totalToken) * 100}%)</Text>
                </View>
                <View style={styles.contentContainer}>
                  <Text style={styles.menu}>배정 수량</Text>
                  <Text style={styles.content}>{itemData.item.tradeNum ? itemData.item.tradeNum : '미정'}</Text>
                </View>
              </View>
              <View style={styles.bottomContainer}>
                <View style={styles.contentContainer}>
                  <Text style={styles.menu}>잔여 수량 환불 예정일</Text>
                  <Text style={styles.content}>{itemData.item.refundDate ? itemData.item.refundDate : '미정' }</Text>
                </View>
                <Text style={styles.state}>{itemData.item.state ? '청약 마감' : '청약 진행중'}</Text>
              </View>
            </View>
          </TouchableWithoutFeedback>
        )
      }}
      keyExtractor={(item) => {
        return item.tradeCode
      }}
      contentContainerStyle={{ flexGrow: 1 }}
    />
  )
}

export default ApplyList;

const styles = StyleSheet.create({
  cardContainer: {
    width: '100%',
    paddingHorizontal: 16,
    paddingVertical: 12,
    justifyContent: 'center',
    borderRadius: 10,
    borderColor: 'black',
    borderWidth: 0.5,
    gap: 3,
    marginBottom: 10,
  },
  topContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center'
  },
  farmName: {
    fontSize: 12,
    fontFamily: 'pretendard-medium'
  },
  contentContainer: {
    gap: 3
  },
  midContainer: {
    flexDirection: 'row',
    gap: 24
  },
  menu: {
    fontSize: 8,
    color: Colors.fontGray,
    fontFamily: 'pretendard-medium'
  },
  content: {
    fontSize: 10,
    fontFamily: 'pretendard-medium'
  },
  bottomContainer: {
    flexDirection: 'row',
    alignItems: 'flex-end',
    justifyContent: 'space-between'
  },
  state: {
    fontSize: 12,
    fontFamily: 'pretendard-medium'
  }
});