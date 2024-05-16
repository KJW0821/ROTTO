import { View, Text, FlatList, StyleSheet, TouchableWithoutFeedback } from 'react-native';
import Colors from '../../constants/Colors';
import dayjs from 'dayjs';
import { useCallback, useState } from 'react';
import { useFocusEffect } from '@react-navigation/native';
import { getApplyHistory } from '../../utils/investApi';

const ApplyList = () => {
  const [data, setData] = useState();

  useFocusEffect(
    useCallback(() => {
      const getApplyData = async () => {
        const res = await getApplyHistory();
        setData(res.userApplyHistoryListGetDtos);
      };

      getApplyData();
    }, [])
  );

  return(
    data &&
    <FlatList 
      data={data}
      renderItem={itemData => {
        return (
          <TouchableWithoutFeedback>
            <View style={styles.cardContainer}>
              <View style={styles.topContainer}>
                <Text style={styles.farmName}>{itemData.item.farmName}</Text>
                <Text style={styles.menu}>{dayjs(itemData.item.startedTime).add(9, 'hour').format('YYYY.MM.DD')} - {dayjs(itemData.item.endTime).add(9, 'hour').format('YYYY.MM.DD')}</Text>
              </View>
              <View style={styles.midContainer}>
                <View style={styles.contentContainer}>
                  <Text style={styles.menu}>신청률</Text>
                  <Text style={styles.content}>{itemData.item.totalApplyCount} / {itemData.item.totalTokenCount} ROT ({Math.round(itemData.item.totalApplyCount / itemData.item.totalTokenCount * 100 * 100) / 100}%)</Text>
                </View>
                <View style={styles.contentContainer}>
                  <Text style={styles.menu}>배정 수량</Text>
                  <Text style={styles.content}>미정 / {itemData.item.applyCount} ROT</Text>
                </View>
              </View>
              <View style={styles.bottomContainer}>
                <View style={styles.contentContainer}>
                  <Text style={styles.menu}>잔여 수량 환불 예정일</Text>
                  <Text style={styles.content}>{itemData.item.refundDate ? dayjs(itemData.item.refundDate).add(9, 'hour').format('YYYY.MM.DD') : '미정' }</Text>
                </View>
                <Text style={styles.state}>
                  {
                    dayjs(itemData.item.endTime) >= dayjs() ? 
                    '청약 진행중'
                    :
                    '청약 마감'
                  }
                </Text>
              </View>
            </View>
          </TouchableWithoutFeedback>
        )
      }}
      keyExtractor={(item) => {
        return item.applyHistoryCode
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