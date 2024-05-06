import { StyleSheet, Text, View } from "react-native";

const MyBanner = () => {
  return (
    <View style={styles.container}>
      <Text>원두 투자 시작해볼까요?</Text>
    </View>
  );
};

export default MyBanner

const styles = StyleSheet.create({
  container: {
    marginTop: 20,
    marginHorizontal: 15,
    height: 140,
    borderRadius: 10,
    padding: 15,
    backgroundColor: "white",
  },
});
