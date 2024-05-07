import { View, Text, StyleSheet, Image } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { useNavigation } from "@react-navigation/native";

import Colors from "../../constants/Colors";

const StackHeader = ({ title, screenName }) => {
  const Navigation = useNavigation();

  return (
    <View style={styles.container}>
      <View style={styles.iconContainer}>
        <Ionicons
          name="chevron-back"
          size={26}
          color="black"
          onPress={() => Navigation.navigate(screenName)}
        />
      </View>
      <Text>{title}</Text>
    </View>
  );
};

export default StackHeader;

const styles = StyleSheet.create({
  container: {
    // flex: 1,
    backgroundColor: "white",
    height: 70,
    justifyContent: "center",
  },
  title: {
    fontFamily: "pretendard-bold",
  },
  iconContainer: {
    flex: 1,
    width: 60,
    marginTop: 20,
    marginLeft: 15,
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
  },
});
