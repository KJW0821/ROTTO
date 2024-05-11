import { ScrollView, View, Pressable, Text, StyleSheet } from "react-native";
import Colors from "../../constants/Colors";
import { Ionicons } from '@expo/vector-icons';
import FilterButton from "./FilterButton";

const FilterBar = () => {
  return (
    <View style={styles.container}>
      <ScrollView horizontal={true} showsHorizontalScrollIndicator={false}>
        <View style={styles.filterContainer}>
          <Pressable
            style={styles.sortButton}
          >
            <Ionicons
              style={styles.filterIcon}
              name="chevron-expand-outline"
              size={20}
              color="black"
            />
            <Text style={styles.filterFont}>기본순</Text>
          </Pressable>
          <FilterButton filterName={"펀딩 진행중"} width={120} />
          <FilterButton filterName={"원두"} width={80} />
          <FilterButton filterName={"가격"} width={80} />
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
    width: 83,
    height: 35,
    borderTopRightRadius: 17.5, // 오른쪽 위 모서리의 border radius
    borderBottomRightRadius: 17.5, // 오른쪽 아래 모서리의 border radius
    backgroundColor: "white",
    justifyContent: "center",
    borderColor: Colors.borderGray,
    borderWidth: 1,
    gap: 4
  },
  filterContainer: {
    flexDirection: "row",
    gap: 8,
    height: 35
  },
  filterFont: {
    fontFamily: "pretendard-medium",
    fontSize: 14,
  },
  filterIcon: {
    marginLeft: -12
  }
});