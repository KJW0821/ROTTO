import { View, Text, ScrollView, StyleSheet, Pressable, Image } from 'react-native';
import DetailTopBar from '../../components/common/DetailTopBar';
import { useFocusEffect } from '@react-navigation/native';
import { useCallback, useState } from 'react';
import { getFundingDetail } from '../../utils/fundingApi';
import dayjs from 'dayjs';
import Colors from '../../constants/Colors';
import { MaterialCommunityIcons, Ionicons } from '@expo/vector-icons';
import CustomButton from '../../components/common/CustomButton';

const FundingDetailScreen = ({navigation, route}) => {
  const subscriptionCode = route.params.subscriptionCode;

  // const [data, setData] = useState();

  // useFocusEffect(
  //   useCallback(() => {
  //     const getDetailedData = async () => {
  //       const res = await getFundingDetail(subscriptionCode);
  //       console.log(res);
  //       setData(res);
  //     };

  //     getDetailedData();
  //   }, [])
  // )

  const data = {
    subscriptionCode: 455,
    farmCode: 11,
    farmName: "섀넌 농장",
    confirmPrice: 10000,
    startedTime: "2024-05-14T17:03:57",
    endTime: "2024-05-19T17:03:57",
    beanType: "과테말라 안티구아",
    limitNum: 5,
    returnRate: 0,
    applyCount: 0,
    totalTokenCount: 0,
    subsStatus: 1
  };

  const getState = (state, startedTime, endTime) => {
    switch (state) {
      case 0:
        return {
          text: `D-${Math.floor(dayjs(startedTime).add(9, 'hour').diff(dayjs(), "day", true))}`,
          color: Colors.btnYellow
        };
      case 1:
        return {
          text: '펀딩 진행중',
          color: Colors.btnBlue,
          endText: `펀딩 마감 D-${Math.floor(dayjs(endTime).add(9, 'hour').diff(dayjs(), "day", true))}`
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
      <DetailTopBar navigation={navigation} />
      {
        data &&
        <ScrollView style={styles.scrollContainer} contentContainerStyle={{ rowGap: 14 }}>
          <View style={styles.badgeContainer}>
            <View style={[styles.badge, { backgroundColor: getState(data.subsStatus, data.startedTime, data.endTime).color }]}>
              <Text style={styles.badgeText}>{getState(data.subsStatus, data.startedTime, data.endTime).text}</Text>
            </View>
            {
              data.subsStatus === 1 &&
              <View style={[styles.badge, { backgroundColor: 'black' }]}>
                <Text style={styles.badgeText}>{getState(data.subsStatus, data.startedTime, data.endTime).endText}</Text>
              </View>
            }
          </View>
          <Text style={styles.farmName}>{data.farmName}</Text>
          <View style={styles.imgContainer}>
            <Image style={styles.img} source={require('../../../assets/images/discovery/coffeefarm2.png')} />
          </View>
          <View style={styles.contentContainer}>
            <View style={styles.menuContainer}>
              <MaterialCommunityIcons name="calendar-blank" size={18} color="black" />
              <Text style={styles.menutext}>공모 기한</Text>
            </View>
            <Text style={styles.contentText}>
              {dayjs(data.startedTime).add(9, 'hour').format('YYYY.MM.DD')} - {dayjs(data.endTime).add(9, 'hour').format('YYYY.MM.DD')}
            </Text>
          </View>
          <View style={styles.contentContainer}>
            <View style={styles.menuContainer}>
              <MaterialCommunityIcons name="registered-trademark" size={18} color="black" />
              <Text style={styles.menuText}>펀딩 목표 수량</Text>
            </View>
            <Text style={styles.contentText}>{data.totalTokenCount} ROTTO</Text>
          </View>
          <View style={styles.contentContainer}>
            <View style={styles.menuContainer}>
              <MaterialCommunityIcons name="registered-trademark" size={18} color="black" />
              <Text style={styles.menuText}>펀딩 최대 수량</Text>
            </View>
            <Text style={styles.contentText}>{data.limitNum} ROTTO</Text>
          </View>
          <View style={styles.contentContainer}>
            <View style={styles.menuContainer}>
              <Ionicons name="pricetags" size={18} color="black" />
              <Text style={styles.menuText}>가격</Text>
            </View>
            <Text style={styles.contentText}>{data.confirmPrice.toLocaleString('ko-KR')}원 / 1 ROTTO</Text>
          </View>
          <View style={styles.contentContainer}>
            <View style={styles.menuContainer}>
              <MaterialCommunityIcons name="chart-line" size={18} color="black" />
              <Text style={styles.menuText}>지난 펀딩 수익률</Text>
            </View>
            <Text style={[styles.contentText, { color: 'red' }]}>+{data.returnRate}%</Text>
          </View>
          <View style={styles.contentContainer}>
            <View style={styles.menuContainer}>
              <Ionicons name="information-circle-outline" size={18} color="black" />
              <Text style={styles.menuText}>상세 정보</Text>
            </View>
            <Pressable>
              <Text style={styles.contentText}>농장 상세 정보 {'>'}</Text>
            </Pressable>
          </View>
        </ScrollView>
      }
      <View style={styles.buttonContainer}>
        <CustomButton>신청하기</CustomButton>
      </View>
    </View>
  )
}

export default FundingDetailScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    backgroundColor: 'white'
  },
  scrollContainer: {
    width: '85%'
  },
  buttonContainer: {
    width: '85%',
    paddingVertical: 16
  },
  badgeContainer: {
    flexDirection: 'row',
    gap: 6,
    alignItems: 'center'
  },
  badge: {
    paddingVertical: 4,
    paddingHorizontal: 12,
    borderRadius: 25
  },
  badgeText: {
    fontSize: 12,
    fontFamily: 'pretendard-semiBold',
    color: 'white'
  },
  farmName: {
    fontSize: 20,
    fontFamily: 'pretendard-semiBold'
  },
  contentContainer: {
    gap: 5
  },
  menuContainer: {
    flexDirection: 'row',
    gap: 8,
    alignItems: 'center'
  },
  menuText: {
    fontSize: 14,
    fontFamily: 'pretendard-medium'
  },
  contentText: {
    fontSize: 12,
    fontFamily: 'pretendard-medium',
    color: Colors.fontGray
  },
  imgContainer: {
    height: 190,
    borderRadius: 5,
    overflow: 'hidden'
  },
  img: {
    height: '100%',
    width: '100%'
  }
})