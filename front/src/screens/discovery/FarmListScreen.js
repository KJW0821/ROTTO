import {
  FlatList,
  Pressable,
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
import { GestureHandlerRootView } from "react-native-gesture-handler";
import BottomSheet from "../../components/common/MyBottomSheet";
import MyPicker from "../../components/common/MyPicker";
import FarmDetail from "../../components/discovery/FarmDetail";

const FarmListScreen = () => {
  const { width, height } = useWindowDimensions();
  const [farms, setFarms] = useState([]);

  const [filters, setFilters] = useState([]);
  // const [params, setParams] = useState({
  //   "sort": null,
  //   "keyword": null,
  //   "interest": null,
  //   "subsStatus": null,
  //   "minPrice": null,
  //   "maxPrice": null,
  //   "beanType": null,
  // });
  const params = useRef({
    sort: null,
    keyword: null,
    interest: null,
    subsStatus: null,
    minPrice: null,
    maxPrice: null,
    beanType: null,
  });
  const [useFilter, setUseFilter] = useState(false);
  const filterType = useRef("");
  const filterIndex = useRef(0);

  const getList = async () => {
    const res = await getFarmList();
    setFarms(res.farms);
  };

  const applyFilter = (name, id) => {
    filterIndex.current = id;
    params.current.sort = name;
  };

  useEffect(() => {
    getList();
  }, []);

  const renderFarmList = (itemData) => {
    return <FarmDetail data={itemData.item} />;
  };

  return (
    <View style={styles.screen}>
      {/* <GestureHandlerRootView> */}
      {/* {filters && (
          <BottomSheet>
            {filters.map((filter) => (
              <Pressable key={filter.index} onPress={() => applyFilter(filter.name, filter.index)}>
                <Text style={filter.index == filterIndex ? styles.filterFontEmp : styles.filterFont}>{filter.name}</Text>
              </Pressable>
            ))}
          </BottomSheet>
        )} */}
      <StackHeader
        screenName={"discovery"}
        title={"농장 검색"}
        color={Colors.bgOrg}
        search={true}
      />
      <View style={styles.filterContainer}>
        <Pressable
          style={styles.sortButton}
          onPress={() => {
            filterType.current = "sort";
            setFilters([]);
            setFilters([
              { index: 0, name: "기본순", value: null },
              { index: 1, name: "최근 수익률 높은 순", value: "rate" },
              { index: 2, name: "마감 기한 빠른 순", value: "deadline" },
              { index: 3, name: "공모가 높은 순", value: "highPrice" },
              { index: 4, name: "공모가 낮은 순", value: "lowPrice" },
            ]);
          }}
          >
          <Ionicons
            style={styles.filterIcon}
            name="chevron-expand-outline"
            size={24}
            color="black"
            />
          <Text style={styles.filterFont}>기본순</Text>
        </Pressable>
      </View>
      <View style={styles.farmsContainer}>
        <FlatList
          data={farms}
          keyExtractor={(item) => item.farmCode}
          renderItem={renderFarmList}
        />
      </View>
      {/* </GestureHandlerRootView> */}
    </View>
  );
};

export default FarmListScreen;

const styles = StyleSheet.create({
  screen: {
    flex: 1,
    backgroundColor: "white",
  },
  sortButton: {
    flexDirection: "row",
    alignItems: "center",
    // justifyContent: 'space-around',
    width: 100,
    height: 40,
    borderTopRightRadius: 20, // 오른쪽 위 모서리의 border radius
    borderBottomRightRadius: 20, // 오른쪽 아래 모서리의 border radius
    backgroundColor: "white",
    borderColor: Colors.borderGray,
    borderWidth: 1,
  },
  filterContainer: {
    marginTop: 10,
  },
  filterIcon: {
    marginLeft: 5,
  },
  filterFont: {
    marginBottom: 3,
    marginLeft: 8,
    fontFamily: "pretendard-regular",
    fontSize: 15,
  },
  filterFontEmp: {
    marginBottom: 3,
    marginLeft: 8,
    fontFamily: "pretendard-bold",
    fontSize: 15,
  },
  farmsContainer: {
    marginTop: 20,
    alignItems: "center",
    justifyContent: "flex-start",
  },
});
