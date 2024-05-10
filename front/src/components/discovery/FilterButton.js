import { Pressable, StyleSheet, Text } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import Colors from "../../constants/Colors";

const FilterButton = ({filterName}) => {
  return (
    <Pressable style={[styles.filterButton]}>
      <Text style={styles.filterFont}>{filterName}</Text>
    </Pressable>
  );
};

export default FilterButton;

const styles = StyleSheet.create({
  filterButton: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: 'center',
    height: 35,
    borderRadius: 5,
    backgroundColor: "white",
    borderColor: Colors.borderGray,
    borderWidth: 1,
    marginLeft: 10,
  },
  filterFont: {
    marginBottom: 3,
    marginHorizontal: 10,
    fontFamily: "pretendard-regular",
    fontSize: 15,
  }
});
