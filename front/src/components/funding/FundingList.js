import { View, FlatList, Text, StyleSheet } from 'react-native';
import { FontAwesome5 } from '@expo/vector-icons';
import Colors from '../../constants/Colors';

const FundingList = ({navigation}) => {
  const data = [
    {
      subscriptionCode: 1,
      farmCode: 1,
      farmName: '농부 주원의 에티오피아 농장',
      startedTime: '2024-05-11',
      endTime: '2024-05-13',
      returnRate: 8,
      applyCount: 140,
      confirmPrice: 10000,
      totalCount: 100,
      state: 0
    },
    {
      subscriptionCode: 2,
      farmCode: 1,
      farmName: '농부 주원의 에티오피아 농장',
      startedTime: '2024-05-11',
      endTime: '2024-05-13',
      returnRate: 8,
      applyCount: 140,
      confirmPrice: 10000,
      totalCount: 100,
      state: 1
    },
    {
      subscriptionCode: 3,
      farmCode: 1,
      farmName: '농부 주원의 에티오피아 농장',
      startedTime: '2024-05-11',
      endTime: '2024-05-13',
      returnRate: 8,
      applyCount: 140,
      confirmPrice: 10000,
      totalCount: 100,
      state: 2
    }
  ];

  const getState = (state, startedTime) => {
    switch (state) {
      case 0:
        return {
          text: 'D-2',
          color: Colors.btnYellow
        };
      case 1:
        return {
          text: '펀딩 진행중',
          color: Colors.btnBlue
        }
      case 2:
        return {
          text: '마감',
          color: Colors.fontGray
        }
    }
  };

  return (
    <View style={styles.container}>
      <FlatList 
        data={data}
        renderItem={itemData => {
          return (
            <View style={styles.cardContainer}>
              <View style={styles.topContainer}>
                <Text style={styles.date}>{itemData.item.startedTime} - {itemData.item.endTime}</Text>
                <View style={[styles.stateContainer, { backgroundColor: getState(itemData.item.state).color }]}>
                  <Text style={styles.state}>{getState(itemData.item.state).text}</Text>
                </View>
              </View>
              <Text style={styles.farmName}>{itemData.item.farmName}</Text>
              <View style={styles.bottomContainer}>
                <View style={styles.contentContainer}>
                  <View style={styles.menuContainer}>
                    <FontAwesome5 name="coins" size={12} />
                    <Text style={styles.menu}>가격</Text>
                  </View>
                  <Text style={styles.content}>{itemData.item.confirmPrice} / 1ROT</Text>
                </View>
                {
                  itemData.item.state === 1 &&
                  <View style={styles.contentContainer}>
                    <View style={styles.menuContainer}>
                      <FontAwesome5 name="chart-pie" size={12} />
                      <Text style={styles.menu}>신청률</Text>
                    </View>
                    <Text style={styles.content}>{itemData.item.applyCount} / {itemData.item.totalCount} ROT ({Math.floor(itemData.item.applyCount / itemData.item.totalCount) * 100}%)</Text>
                  </View>
                }
              </View>
            </View>
          )
        }}
        keyExtractor={(item) => {
          return item.subscriptionCode
        }}
        contentContainerStyle={{ flexGrow: 1 }}
      />
    </View>
  )
}

export default FundingList;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
    marginTop: 16,
    borderTopLeftRadius: 25,
    borderTopRightRadius: 25,
    padding: 16
  },
  cardContainer: {
    paddingHorizontal: 16,
    paddingVertical: 10,
    borderRadius: 10,
    borderColor: Colors.fontGray,
    borderWidth: 0.5,
    gap: 12,
    marginBottom: 16
  },
  topContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center'
  },
  bottomContainer: {
    flexDirection: 'row',
    gap: 28,
    alignContent: 'center'
  },
  date: {
    fontSize: 10,
    fontFamily: 'pretendard-medium',
    color: Colors.fontGray
  },
  state: {
    fontSize: 10,
    fontFamily: 'pretendard-bold',
    color: 'white'
  },
  stateContainer: {
    height: 20,
    width: 65,
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 10
  },
  farmName: {
    fontSize: 16,
    fontFamily: 'pretendard-medium'
  },
  menu: {
    fontSize: 14,
    fontFamily: 'pretendard-medium'
  },
  menuContainer: {
    flexDirection: 'row',
    gap: 6,
    alignItems: 'center'
  },
  content: {
    fontSize: 12,
    fontFamily: 'pretendard-medium',
    color: Colors.fontGray
  },
  contentContainer: {
    gap: 5
  }
});