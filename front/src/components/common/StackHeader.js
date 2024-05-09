import { View, Text, StyleSheet, Image } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { useNavigation } from "@react-navigation/native";

import Colors from "../../constants/Colors";

const StackHeader = ({ title, screenName, color, search }) => {
  const Navigation = useNavigation();

  return (
    <View
      style={[
        styles.container,
        color ? { backgroundColor: color } : { backgroundColor: "white" },
      ]}
    >
      <View style={styles.iconContainer}>
        <Ionicons
          name="chevron-back"
          size={26}
          color={color ? "white" : "black"}
          onPress={() => Navigation.navigate(screenName)}
        />
      </View>
      <View>
        <Text
          style={[
            styles.title,
            color ? { color: "white" } : { color: "black" },
          ]}
        >
          {title}
        </Text>
      </View>
      {search ? (
        <View style={styles.iconContainer}>
          <Ionicons name="search" size={24} color={color ? "white" : "black"} />
        </View>
      ) : (
        <View></View>
      )}
    </View>
  );
};

export default StackHeader;

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    backgroundColor: "white",
    height: 70,
    justifyContent: "space-between",
    alignItems: "center",
  },
  title: {
    fontFamily: "pretendard-bold",
    fontSize: 20,
  },
  iconContainer: {
    marginHorizontal: 15,
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
  },
});
