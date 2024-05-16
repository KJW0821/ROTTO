import { ScrollView, View, Pressable, Text, StyleSheet } from "react-native";
import Colors from "../../constants/Colors";
import { Ionicons } from '@expo/vector-icons';
import FilterButton from "../discovery/FilterButton";
import { useDispatch, useSelector } from "react-redux";
import { setFilterData, setFilterModal, setSortBy } from "../../stores/fundingSlice";

const FilterBar = () => {
  const { 
    sortBy, 
    subsStatus, 
    minPrice, 
    maxPrice, 
    beanType,
  } = useSelector(state => state.filterInfo);

  const dispatch = useDispatch();

  const filters = {
    sortBy: [
      { value:  null, text: '기본순'},
      { value: 'rate', text: '수익률 높은 순' },
      { value: 'deadline', text: '마감 기한 빠른 순' },
      { value: 'highPrice', text: '공모가 높은 순' },
      { value: 'lowPrice', text: '공모가 낮은 순' },
      { value: 'highApplyPercent', text: '신청률 높은 순' }
    ],
    subsStatus: [
      { value: null, text: '전체' },
      { value: 0, text: '펀딩 예정' },
      { value: 1, text: '펀딩 진행중' },
      { value: 2, text: '펀딩 마감' }
    ],
    beanType: [
      { text: '선택 안함', value: null },
      { text: '브라질 산토스', value: '브라질 산토스' },
      { text: '콜롬비아 수프리모', value: '콜롬비아 수프리모' },
      { text: '자메이카 블루마운틴', value: '자메이카 블루마운틴' },
      { text: '에티오피아 예가체프', value: '에티오피아 예가체프' },
      { text: '케냐 AA', value: '케냐 AA' },
      { text: '코스타리카 따라주', value: '코스타리카 따라주' },
      { text: '탄자니아 AA', value: '탄자니아 AA' },
      { text: '예멘 모카 마타리', value: '예멘 모카 마타리' },
      { text: '하와이 코나', value: '하와이 코나' },
      { text: '과테말라 안티구아', value: '과테말라 안티구아' },
      { text: '파나마 게이샤', value: '파나마 게이샤' },
      { text: '엘살바도르', value: '엘살바도르' }
    ]
  };

  return (
    <View style={styles.container}>
      <ScrollView horizontal={true} showsHorizontalScrollIndicator={false}>
        <View style={styles.filterContainer}>
          <Pressable
            style={styles.sortButton}
            onPress={() => {
              dispatch(setFilterData(filters.sortBy));
              dispatch(setFilterModal(true));
            }}
          >
            <Text style={styles.filterFont}>{sortBy ? sortBy : '기본순'}</Text>
            <Ionicons
              style={styles.filterIcon}
              name="chevron-down"
              size={20}
              color={Colors.iconGray}
            />
          </Pressable>
          <FilterButton 
            filterName={subsStatus ? subsStatus : '진행 여부'} 
            isChecked={subsStatus} 
            onPress={() => {
              dispatch(setFilterData(filters.subsStatus));
              dispatch(setFilterModal(true));
            }} 
          />
          <FilterButton 
            filterName={beanType ? beanType : '원두'} 
            isChecked={beanType} 
            onPress={() => {
              dispatch(setFilterData(filters.beanType));
              dispatch(setFilterModal(true));
            }} 
          />
          <FilterButton 
            filterName={
              (!minPrice && !maxPrice) ? "가격" 
              : (!minPrice) ? `가격 ${maxPrice}원 이하`
              : (!maxPrice) ? `가격 ${minPrice}원 이상`
              : `가격 ${minPrice}원 ~ ${maxPrice}원`
            } 
            isChecked={minPrice || maxPrice} 
            onPress={() => {
              dispatch(setFilterData(null));
              dispatch(setFilterModal(true));
            }} 
          />
        </View>
      </ScrollView>
    </View>
  )
}

export default FilterBar;

const styles = StyleSheet.create({
  container: {
    height: 35,
    marginTop: 10
  },
  sortButton: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    height: 35,
    marginLeft: 10,
    borderRadius: 5,
    backgroundColor: "white",
    borderColor: Colors.borderGray,
    borderWidth: 1,
  },
  filterContainer: {
    flexDirection: "row",
    height: 35
  },
  filterIcon: {
    marginLeft: 5,
    paddingRight: 4
  },
  filterFont: {
    marginBottom: 3,
    marginLeft: 8,
    fontFamily: "pretendard-bold",
    fontSize: 15,
  },
});