import { View, Text, StyleSheet, FlatList, TouchableWithoutFeedback } from 'react-native';
import Colors from '../../constants/Colors';

const TradeList = () => {
  const data = [
    {
      tradeCode: 1,
      farmName: '농부 주원의 에티오피아 농장',
      confirmPrice: 1000000,
      tradeTime: '2024-04-25',
      tradeNum: 10,
      refund: 0
    },
    {
      tradeCode: 2,
      farmName: '농부 다민의 에티오피아 농장',
      confirmPrice: 1000000,
      tradeTime: '2024-04-22',
      tradeNum: 10,
      refund: 0
    },
    {
      tradeCode: 3,
      farmName: '농부 준형의 에티오피아 농장',
      confirmPrice: 1000000,
      tradeTime: '2024-04-21',
      tradeNum: 10,
      refund: 1
    },
    {
      tradeCode: 4,
      farmName: '농부 유정의 에티오피아 농장',
      confirmPrice: 1000000,
      tradeTime: '2024-04-20',
      tradeNum: 10,
      refund: 1
    },
    {
      tradeCode: 5,
      farmName: '농부 세훈의 에티오피아 농장',
      confirmPrice: 1000000,
      tradeTime: '2024-04-18',
      tradeNum: 10,
      refund: 1
    },
    {
      tradeCode: 6,
      farmName: '농부 형욱의 에티오피아 농장',
      confirmPrice: 1000000,
      tradeTime: '2024-04-17',
      tradeNum: 10,
      refund: 1
    }
  ]

  return (
    <FlatList 
      data={data}
      renderItem={itemData => {
        return (
          <TouchableWithoutFeedback>
            <View style={styles.cardContainer}>
              <Text style={styles.farmName}>{itemData.item.farmName}</Text>
              <View style={styles.contentContainer}>
                <Text style={styles.menu}>내 보유 ROTTO</Text>
                <Text style={styles.content}>{itemData.item.tradeNum} ROT</Text>
              </View>
              <View style={styles.bottomContainer}>
                <View style={styles.contentContainer}>
                  <Text style={styles.menu}>내 투자 금액</Text>
                  <Text style={styles.content}>{itemData.item.confirmPrice} 원</Text>
                </View>
                <Text style={styles.state}>{itemData.item.refund ? '출하' : '재배중'}</Text>
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

export default TradeList;

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
  farmName: {
    fontSize: 12,
    fontFamily: 'pretendard-medium'
  },
  contentContainer: {
    gap: 3
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
})