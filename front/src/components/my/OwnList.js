import { View, Text, FlatList, StyleSheet, Pressable } from 'react-native';
import Colors from '../../constants/Colors';
import { useCallback, useState } from 'react';
import { useFocusEffect } from '@react-navigation/native';
import { MaterialIcons } from '@expo/vector-icons';

const OwnList = () => {

  return(
    data &&
    <FlatList 
      data={data}
      renderItem={itemData => {
        return (
          <View style={styles.cardContainer}>
            <View style={styles.topContainer}>
              <Text style={styles.farmName}>{itemData.item.farmName}</Text>
              <Pressable style={styles.buttonContainer}>
                <Text style={styles.menu}>상세 내역</Text>
                <MaterialIcons name="arrow-forward-ios" size={14} />
              </Pressable>
            </View>
            <View style={styles.contentContainer}>
              <Text style={styles.menu}>내 지분</Text>
              <Text style={styles.content}>{itemData.item.tradeNum} / {itemData.item.totalTokenCount} ROT ({Math.round(itemData.item.tradeNum / itemData.item.totalTokenCount * 100 * 100) / 100}%)</Text>
            </View>
            <View style={styles.contentContainer}>
              <Text style={styles.menu}>수익 금액</Text>
              <Text style={[styles.content, { color: itemData.item.proceed >= 0 ? 'red' : 'blue' }]}>
                {itemData.item.proceed >= 0 ? 
                `+${itemData.item.proceed.toLocaleString('ko-KR')}` 
                : 
                `-${itemData.item.proceed.toLocaleString('ko-KR')}`}
              </Text>
            </View>
          </View>
        )
      }}
      keyExtractor={(item) => {
        return item.applyHistoryCode
      }}
      contentContainerStyle={{ flexGrow: 1 }}
    />
  )
}

export default OwnList;

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
    fontSize: 14,
    fontFamily: 'pretendard-semiBold'
  },
  contentContainer: {
    gap: 3
  },
  menu: {
    fontSize: 10,
    color: Colors.fontGray,
    fontFamily: 'pretendard-medium'
  },
  content: {
    fontSize: 12,
    fontFamily: 'pretendard-medium'
  },
  buttonContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 4
  }
});