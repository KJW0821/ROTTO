import {
  FlatList,
  Pressable,
  ScrollView,
  StyleSheet,
  Text,
  View,
  useWindowDimensions,
} from "react-native";
import { useEffect, useRef, useState } from "react";
import { getFarmList } from "../../utils/farmAPi";
import { Ionicons } from "@expo/vector-icons";

import Colors from "../../constants/Colors";
import StackHeader from "../../components/common/StackHeader";
import MyBottomSheet from "../../components/common/MyBottomSheet";
import { GestureHandlerRootView } from "react-native-gesture-handler";
import FarmDetail from "../../components/discovery/FarmDetail";
import FilterButton from "../../components/discovery/FilterButton";

let sortData = [
  { index: 0, name: "기본순", value: null },
  { index: 1, name: "최근 수익률 높은 순", value: "rate" },
  { index: 2, name: "마감 기한 빠른 순", value: "deadline" },
  { index: 3, name: "공모가 높은 순", value: "highPrice" },
  { index: 4, name: "공모가 낮은 순", value: "lowPrice" },
];

const FarmListScreen = () => {
  const { width, height } = useWindowDimensions();
  const [farms, setFarms] = useState([]);
  const [isBottomSheetOpen, setIsBottomSheetOpen] = useState(false);
  const [selectedSort, setSelectedSort] = useState(sortData[0]); // 기본순 선택

  const getList = async () => {
    const res = await getFarmList();
    setFarms(res.farms);
  };

  const applyFilter = (name, id) => {
    setSelectedSort(sortData.find((item) => item.index === id));
    setIsBottomSheetOpen(false); // 바텀시트 닫기
  };

  useEffect(() => {
    getList();
  }, []);

  const renderFarmList = (itemData) => {
    return <FarmDetail data={itemData.item} />;
  };

  return (
    <View style={styles.screen}>
      <StackHeader
        screenName={"discovery"}
        title={"농장 검색"}
        color={Colors.bgOrg}
        search={true}
      />
      <ScrollView
        contentContainerStyle={styles.topScroll}
        horizontal={true}
        showsHorizontalScrollIndicator={false}
      >
        <View style={styles.filterContainer}>
          <Pressable
            style={styles.sortButton}
            onPress={() => {
              setIsBottomSheetOpen(true);
            }}
          >
            <Text style={styles.filterFont}>{selectedSort.name}</Text>
            <Ionicons
              style={styles.filterIcon}
              name="chevron-down"
              size={20}
              color={Colors.iconGray}
            />
          </Pressable>
          <FilterButton filterName={"청약 진행중"} />
          <FilterButton filterName={"원두"} />
          <FilterButton filterName={"관심 농장"} />
        </View>
      </ScrollView>
      <FlatList
        contentContainerStyle={styles.farmsContainer}
        showsVerticalScrollIndicator={false}
        data={farms}
        keyExtractor={(item) => item.farmCode}
        renderItem={renderFarmList}
      />
      <MyBottomSheet isOpen={isBottomSheetOpen}>
        <ScrollView>
          {sortData.map((sortItem) => (
            <Pressable
              key={sortItem.index}
              onPress={() => applyFilter(sortItem.name, sortItem.index)}
              style={styles.sortItem}
            >
              <Text style={styles.sortItemText}>{sortItem.name}</Text>
            </Pressable>
          ))}
        </ScrollView>
      </MyBottomSheet>
    </View>
  );
};

export default FarmListScreen;

const styles = StyleSheet.create({
  screen: {
    flex: 1,
    backgroundColor: "white",
  },
  topScroll: {
    height: "auto",
  },
  sortButton: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    width: 100,
    height: 40,
  },
  filterContainer: {
    marginTop: 10,
    height: 50,
    flexDirection: "row",
  },
  filterIcon: {
    marginLeft: 5,
  },
  filterFont: {
    marginBottom: 3,
    marginLeft: 8,
    fontFamily: "pretendard-bold",
    fontSize: 15,
  },
  filterFontEmp: {
    marginBottom: 3,
    marginLeft: 8,
    fontFamily: "pretendard-bold",
    fontSize: 15,
  },
  farmsContainer: {
    alignItems: "center",
    marginTop: 10,
  },
  sortItem: {
    paddingVertical: 10,
    paddingHorizontal: 20,
    borderBottomWidth: 1,
    borderBottomColor: Colors.borderGray,
  },
  sortItemText: {
    fontSize: 16,
  },
});
