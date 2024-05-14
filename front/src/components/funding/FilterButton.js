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
        size={20}
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
    justifyContent: "center",
    height: 35,
    borderRadius: 17.5,
    backgroundColor: "white",
    borderColor: Colors.borderGray,
    borderWidth: 1,
    gap: 4
  },
  filterFont: {
    fontFamily: "pretendard-medium",
    fontSize: 14
  },
  filterIcon: {
    marginRight: -4
  }
});
