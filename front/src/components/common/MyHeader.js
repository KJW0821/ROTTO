import { View, Text, StyleSheet, Image } from "react-native";
import { Ionicons } from "@expo/vector-icons";

import Colors from "../../constants/Colors";

const MyHeader = ({ children }) => {
  return (
    <View style={styles.container}>
      <View style={styles.imageContainer}>
        <Image
          style={styles.appLogo}
          source={require("../../../assets/images/RottoLogo.png")}
        />
        <View style={styles.iconContainer}>
          <Ionicons name="notifications-outline" size={26} color="white" />
          <Ionicons name="person-outline" size={26} color="white" />
        </View>
      </View>
      {children}
    </View>
  );
};

export default MyHeader;

const styles = StyleSheet.create({
  appLogo: {
    marginLeft: 10,
  },
  container: {
    flex: 1,
    backgroundColor: Colors.bgOrg,
  },
  imageContainer: {
    flexDirection: "row",
    justifyContent: "space-between",
  },
  iconContainer: {
    width: 60,
    marginRight: 20,
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between"
  },
  text: {
    color: "white",
  },
});
