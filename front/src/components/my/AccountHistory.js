import { View, Text, StyleSheet, FlatList, Pressable } from 'react-native';
import { useDispatch, useSelector } from 'react-redux';
import { Ionicons } from '@expo/vector-icons';
import Colors from '../../constants/Colors';
import { useFocusEffect } from '@react-navigation/native';
import { useCallback, useEffect, useState } from 'react';
import FilterModal from './FilterModal';
import { setFilterModal } from '../../stores/mySlice';
import { getAccountHistory } from '../../utils/accountApi';
import dayjs from 'dayjs';

const AccountHistory = () => {
  const dispatch = useDispatch();
  const fundingAccount = useSelector(state => state.myPageInfo.fundingAccount);
  const filter = useSelector(state => state.myPageInfo.selectedFilter);

  const [data, setData] = useState();
  const [sections, setSections] = useState({});

  useFocusEffect(
    useCallback(() => {
      const getTransactionData = async () => {
        if (filter === '전체') {
          const res = await getAccountHistory(fundingAccount.accountCode);
          console.log(res);
          setData(res.accountHistoryListDtoss);
        }
      };

      getTransactionData();
    }, [filter])
  );

  const devideSection = () => {
    const sections = {};

    data.forEach((el) => {
      const date = dayjs(el.accountTime).subtract(9, 'hour').format("YYYY년 MM월 DD일");
      if (sections[date]) {
        sections[date].push(el);
      } else {
        sections[date] = [el];
      }
    });

    return sections;
  };

  useEffect(() => {
    if (data) {
      setSections(() => devideSection());
    }
  }, [data])

  useEffect(() => {
    console.log(sections)
  }, [sections])

  return (
    <View style={styles.container}>
      <View style={styles.filterContainer}>
        <Pressable style={styles.filter} onPress={() => dispatch(setFilterModal(true))}>
          <Text style={styles.filterText}>{filter}</Text>
          <Ionicons name="chevron-down" size={20} color={Colors.fontGray} />
        </Pressable>
        <Ionicons name="reload" size={24} color={Colors.fontGray} />
      </View>
      <FilterModal />
    </View>
  )
}

export default AccountHistory;

const styles = StyleSheet.create({
  container: {
    width: '90%',
    gap: 16,
    paddingTop: 12
  },
  filterContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center'
  },
  filter: {
    borderWidth: 1,
    borderColor: Colors.fontGray,
    borderRadius: 25,
    paddingHorizontal: 14,
    paddingVertical: 4,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    gap: 6
  },
  filterText: {
    fontSize: 14,
    fontFamily: 'pretendard-medium',
    color: Colors.fontGray
  }
});