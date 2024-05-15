import { View, FlatList, Text, StyleSheet } from 'react-native';
import { FontAwesome5 } from '@expo/vector-icons';
import Colors from '../../constants/Colors';
import { useEffect, useState } from 'react';
import { getFundingList } from '../../utils/fundingApi';
import dayjs from 'dayjs';

const FundingList = ({navigation}) => {
  const [data, setData] = useState();

  useEffect(() => {
    const getFundingData = async () => {
      const res = await getFundingList();
      setData(res.subscriptions);
    };

    getFundingData();
  }, [navigation])

  const getState = (state, startedTime) => {
    switch (state) {
      case 0:
        return {
          text: `D-${Math.floor(dayjs(startedTime).diff(dayjs(), "day", true))}`,
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
                <Text style={styles.date}>
                  {dayjs(itemData.item.startedTime).format('YYYY.MM.DD')} - {dayjs(itemData.item.endTime).format('YYYY.MM.DD')}
                  </Text>
                <View style={[styles.stateContainer, { backgroundColor: getState(itemData.item.subsStatus, itemData.item.startedTime).color }]}>
                  <Text style={styles.state}>{getState(itemData.item.subsStatus, itemData.item.startedTime).text}</Text>
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
                  itemData.item.subsStatus === 1 &&
                  <View style={styles.contentContainer}>
                    <View style={styles.menuContainer}>
                      <FontAwesome5 name="chart-pie" size={12} />
                      <Text style={styles.menu}>신청률</Text>
                    </View>
                    <Text style={styles.content}>
                      {itemData.item.applyCount} / {itemData.item.totalTokenCount} ROT ({Math.round(itemData.item.applyCount / itemData.item.totalTokenCount * 100 * 100) / 100}%)
                    </Text>
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