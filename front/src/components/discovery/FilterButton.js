import { Pressable, StyleSheet, Text } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import Colors from "../../constants/Colors";

const FilterButton = ({filterName, width}) => {
  return (
    <Pressable style={[styles.filterButton, {width: width}]}>
      <Text style={styles.filterFont}>{filterName}</Text>
      <Ionicons
        style={styles.filterIcon}
        name="chevron-down"
        size={24}
        color="black"
      />
    </Pressable>
  );
};

export default FilterButton;

const styles = StyleSheet.create({
  filterButton: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: 'space-around',
    height: 40,
    borderRadius: 20,
    backgroundColor: "white",
    borderColor: Colors.borderGray,
    borderWidth: 1,
    marginLeft: 10,
  },
  filterContainer: {
    marginTop: 10,
  },
  filterIcon: {
    marginRight: 5,
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
