import { useState } from "react";
import { Pressable, StyleSheet, Text, View } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import Colors from "../../constants/Colors";

const MyDeposit = () => {
  const totalDeposit = "1,000,000";
  const variance = "10.5 %";
  const [isClicked, setIsClicked] = useState(false);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>내 투자</Text>
      <Text style={styles.content}>총 투자금액</Text>
      <Text style={styles.content}>
        <Text style={styles.highlight}>{totalDeposit}</Text> 원
      </Text>
      <Text style={styles.variance}>{"( " + "+ " + variance + " )"}</Text>
      {!isClicked && (
        <Pressable
          style={styles.iconContainer}
          onPress={() => setIsClicked(true)}
        >
          <Ionicons name="chevron-down" size={24} color={Colors.iconGray} />
        </Pressable>
      )}
      {isClicked && (
        <View>
          <View style={styles.line}></View>
          <View style={styles.depositDetail}>
            <Text style={styles.content}>내 상품</Text>
          </View>
          <Pressable
            style={styles.iconContainer}
            onPress={() => setIsClicked(false)}
          >
            <Ionicons name="chevron-up" size={24} color={Colors.iconGray} />
          </Pressable>
        </View>
      )}
    </View>
  );
};

export default MyDeposit;

const styles = StyleSheet.create({
  container: {
    marginTop: 25,
    marginHorizontal: 15,
    // height: 200,
    borderRadius: 10,
    padding: 25,
    backgroundColor: "white",
  },
  iconContainer: {
    marginTop: 15,
    justifyContent: "center",
    alignItems: "center",
  },
  title: {
    fontFamily: "pretendard-bold",
    fontSize: 17,
    marginBottom: 10,
  },
  content: {
    fontFamily: "pretendard-regular",
    fontSize: 17,
  },
  highlight: {
    fontFamily: "pretendard-bold",
    fontSize: 25,
  },
  variance: {
    fontFamily: "pretendard-regular",
    fontSize: 15,
    color: "red",
  },
  depositDetail: {
    marginTop: 20,
    // backgroundColor: "#f1ebeb",
  },
  line: {
    // height:1,
    marginTop: 20,
    borderColor: Colors.fontGray,
    borderWidth: 0.5,
  },
});
