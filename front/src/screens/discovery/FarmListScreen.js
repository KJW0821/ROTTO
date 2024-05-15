import {
  FlatList,
  Pressable,
  ScrollView,
  StyleSheet,
  Text,
  TextInput,
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
import ResetButton from "../../components/discovery/ResetButton";

let sortData = [
  { index: 0, name: "기본순", value: null },
  { index: 1, name: "최근 수익률 높은 순", value: "rate" },
  { index: 2, name: "마감 기한 빠른 순", value: "deadline" },
  { index: 3, name: "공모가 높은 순", value: "highPrice" },
  { index: 4, name: "공모가 낮은 순", value: "lowPrice" },
];

let fundingData = [
  { index: 0, name: "선택 안함", value: null },
  { index: 1, name: "진행 예정", value: 0 },
  { index: 2, name: "진행중", value: 1 },
];

let beanData = [
  { index: 0, name: "선택 안함", value: null },
  { index: 1, name: "브라질 산토스", value: "브라질 산토스" },
  { index: 2, name: "콜롬비아 수프리모", value: "콜롬비아 수프리모" },
  { index: 3, name: "자메이카 블루마운틴", value: "자메이카 블루마운틴" },
  { index: 4, name: "에티오피아 예가체프", value: "에티오피아 예가체프" },
  { index: 5, name: "케냐 AA", value: "케냐 AA" },
  { index: 6, name: "코스타리카 따라주", value: "코스타리카 따라주" },
  { index: 7, name: "탄자니아 AA", value: "탄자니아 AA" },
  { index: 8, name: "예멘 모카 마타리", value: "예멘 모카 마타리" },
  { index: 9, name: "하와이 코나", value: "하와이 코나" },
  { index: 10, name: "과테말라 안티구아", value: "과테말라 안티구아" },
  { index: 11, name: "파나마 게이샤", value: "파나마 게이샤" },
  { index: 12, name: "엘살바도르", value: "엘살바도르" },
];

let deviceWidth;

const FarmListScreen = () => {
  const { width, height } = useWindowDimensions();
  const [farms, setFarms] = useState([]);
  const [isBottomSheetOpen, setIsBottomSheetOpen] = useState(false);
  const [selectedSort, setSelectedSort] = useState(sortData[0]); // 기본순 선택
  const [fundingStatus, setFundingStatus] = useState(fundingData[0]); // 청약 진행 여부 선택
  const [selectedBean, setSelectedBean] = useState(beanData[0]); // 원두 종류 선택
  const [isLiked, setIsLiked] = useState(null);
  const [minPrice, setMinPrice] = useState(null); // 최저 가격 선택
  const [maxPrice, setMaxPrice] = useState(null); // 최고 가격 선택
  const [selectedCategory, setSelectedCategory] = useState(null); // 바텀 시트 표시 내용 선택
  const [keyword, setKeyword] = useState("");

  deviceWidth = width;

  const getList = async (
    sort,
    keyword,
    isLiked,
    subsStatus,
    minPrice,
    maxPrice,
    beanType
  ) => {
    const res = await getFarmList(
      sort,
      keyword,
      isLiked,
      subsStatus,
      minPrice,
      maxPrice,
      beanType
    );
    setFarms(res.farms);
  };

  const applyFilter = (category, name, id) => {
    if (category === "sort") {
      setSelectedSort(sortData.find((item) => item.index === id));
    } else if (category === "funding") {
      setFundingStatus(fundingData.find((item) => item.index === id));
    } else if (category === "bean") {
      setSelectedBean(beanData.find((item) => item.index === id));
    } else if (category === "search") {
    }
    setIsBottomSheetOpen(false); // 바텀시트 닫기
  };

  useEffect(() => {
    getList(
      selectedSort.value,
      keyword,
      isLiked ? true : null,
      fundingStatus.value,
      minPrice,
      maxPrice,
      selectedBean.value
    );
  }, [
    selectedSort,
    keyword,
    isLiked,
    fundingStatus,
    minPrice,
    maxPrice,
    selectedBean,
  ]);

  const renderFarmList = (itemData) => {
    return <FarmDetail data={itemData.item} />;
  };

  const handleGestureEvent = (event) => {
    const { translationY } = event.nativeEvent;
    if (translationY > 0) {
      setIsBottomSheetOpen(false); // 바텀 시트를 닫습니다.
    }
  };

  const handlePressResetButton = () => {
    setSelectedSort(sortData[0]);
    setFundingStatus(fundingData[0]);
    setSelectedBean(beanData[0]);
    setIsLiked(null);
    setMinPrice(null);
    setMaxPrice(null);
  };

  return (
    <View style={styles.screen}>
      <StackHeader
        screenName={"discovery"}
        title={"농장 검색"}
        color={Colors.bgOrg}
        search={true}
        onPress={() => {
          setIsBottomSheetOpen(true);
          setSelectedCategory("search");
        }}
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
              setSelectedCategory("sort");
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
          { (selectedSort != sortData[0] || selectedBean != beanData[0] || minPrice != null ||
          maxPrice != null || fundingStatus != fundingData[0] || isLiked == true) &&
            <ResetButton filterName={"초기화"} onPress={handlePressResetButton} />}
          <FilterButton
            filterName={fundingStatus.index != 0 ? fundingStatus.name : "청약"}
            onPress={() => {
              setIsBottomSheetOpen(true);
              setSelectedCategory("funding");
            }}
          />
          <FilterButton
            filterName={selectedBean.index != 0 ? selectedBean.name : "원두"}
            onPress={() => {
              setIsBottomSheetOpen(true);
              setSelectedCategory("bean");
            }}
          />
          <FilterButton
            filterName={"관심 농장"}
            isChecked={isLiked}
            onPress={() => {
              setIsLiked(!isLiked);
            }}
          />
          <FilterButton
            filterName={"가격"}
            onPress={() => {
              setIsBottomSheetOpen(true);
              setSelectedCategory("price");
            }}
          />
        </View>
      </ScrollView>
      <FlatList
        contentContainerStyle={styles.farmsContainer}
        showsVerticalScrollIndicator={false}
        data={farms}
        keyExtractor={(item) => item.farmCode}
        renderItem={renderFarmList}
      />
      <MyBottomSheet
        isOpen={isBottomSheetOpen}
        onGestureEvent={handleGestureEvent}
      >
        <ScrollView>
          {selectedCategory === "sort" &&
            sortData.map((sortItem) => (
              <Pressable
                key={sortItem.index}
                onPress={() =>
                  applyFilter("sort", sortItem.name, sortItem.index)
                }
                style={styles.sortItem}
              >
                <Text style={styles.sortItemText}>{sortItem.name}</Text>
              </Pressable>
            ))}
          {selectedCategory === "funding" &&
            fundingData.map((item) => (
              <Pressable
                key={item.index}
                onPress={() => applyFilter("funding", item.name, item.index)}
                style={styles.sortItem}
              >
                <Text style={styles.sortItemText}>{item.name}</Text>
              </Pressable>
            ))}
          {selectedCategory === "bean" &&
            beanData.map((item) => (
              <Pressable
                key={item.index}
                onPress={() => applyFilter("bean", item.name, item.index)}
                style={styles.sortItem}
              >
                <Text style={styles.sortItemText}>{item.name}</Text>
              </Pressable>
            ))}
          {selectedCategory === "price" && (
            <View style={styles.priceContainer}>
              <Text>가격</Text>
              <View style={styles.inputsContainer}>
                <View style={styles.inputContainer}>
                  <TextInput
                    maxLength={10}
                    autoCapitalize="none"
                    autoCorrect={false}
                    onChangeText={(minPrice) => setMinPrice(minPrice)}
                    placeholder="최소 가격"
                    keyboardType="number-pad"
                    value={minPrice}
                  />
                  <Text> 원</Text>
                </View>
                <Text> ~ </Text>
                <View style={styles.inputContainer}>
                  <TextInput
                    maxLength={10}
                    autoCapitalize="none"
                    autoCorrect={false}
                    onChangeText={(maxPrice) => setMaxPrice(maxPrice)}
                    placeholder="최대 가격"
                    keyboardType="number-pad"
                    value={maxPrice}
                  />
                  <Text> 원</Text>
                </View>
              </View>
            </View>
          )}
          {selectedCategory === "search" && (
            <View style={styles.searchContainer}>
              <View style={styles.searchInputContainer}>
                <Ionicons
                  name="search"
                  size={24}
                  color={Colors.fontGray}
                  style={{ marginRight: 10 }}
                />
                <TextInput
                  maxLength={10}
                  autoCapitalize="none"
                  autoCorrect={false}
                  onChangeText={(keyword) => setKeyword(keyword)}
                  value={keyword}
                  placeholder="검색"
                />
              </View>
            </View>
          )}
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
    paddingRight: 15,
  },
  sortButton: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    height: 40,
    marginLeft: 10,
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
    minHeight: 800,
    alignItems: "center",
    // justifyContent: "flex-start",
    marginTop: 0,
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
  inputContainer: {
    flexDirection: "row",
    alignItems: "center",
    borderRadius: 3,
    borderColor: Colors.borderGray,
    borderWidth: 1,
    padding: 5,
    paddingHorizontal: 15,
    width: 150,
    justifyContent: "space-between",
  },
  inputsContainer: {
    width: deviceWidth,
    marginTop: 20,
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
  },
  priceContainer: {
    marginTop: 10,
    marginHorizontal: 20,
  },
  searchContainer: {
    marginTop: 10,
    marginHorizontal: 20,
  },
  searchInputContainer: {
    flexDirection: "row",
    borderRadius: 5,
    borderWidth: 1,
    borderColor: Colors.borderGray,
    paddingVertical: 5,
    paddingHorizontal: 10,
    alignItems: "center",
  },
});
