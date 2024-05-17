import { View, Text, FlatList, StyleSheet } from 'react-native';
import Colors from '../../constants/Colors';
import dayjs from 'dayjs';
import { useCallback, useState } from 'react';
import { useFocusEffect } from '@react-navigation/native';
import { getCancelHistory } from '../../utils/investApi';

const CancelList = () => {
  const [data, setData] = useState();

  useFocusEffect(
    useCallback(() => {
      const getCancelData = async () => {
        const res = await getCancelHistory();
        setData(res.userApplyHistoryListCancelDtos);
      };

      getCancelData();
    }, [])
  );

  return(
    data &&
    <FlatList 
      data={data}
      renderItem={itemData => {
        return (
          <View style={styles.cardContainer}>
            <View style={styles.innerContainer}>
              <Text style={styles.farmName}>{itemData.item.farmName}</Text>
              <Text style={styles.menu}>{dayjs(itemData.item.applyTime).add(9, 'hour').format('YYYY.MM.DD')}</Text>
            </View>
            <View style={styles.innerContainer}>
              <View style={styles.contentContainer}>
                <Text style={styles.menu}>신청 수량</Text>
                <Text style={styles.content}>10 / 10 ROT</Text>
              </View>
              <Text style={styles.state}>중도 해지</Text>
            </View>
          </View>
        )
      }}
      keyExtractor={(item) => {
        return item.subscriptionCode
      }}
      contentContainerStyle={{ flexGrow: 1 }}
    />
  )
}

export default CancelList;

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
  innerContainer: {
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
  menu: {
    fontSize: 8,
    color: Colors.fontGray,
    fontFamily: 'pretendard-medium'
  },
  content: {
    fontSize: 10,
    fontFamily: 'pretendard-medium'
  },
  state: {
    fontSize: 12,
    fontFamily: 'pretendard-medium',
    color: 'red'
  }
});