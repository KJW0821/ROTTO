import { StyleSheet, Text, View } from "react-native";
import { useNavigation } from "@react-navigation/native";

const MyBanner = () => {
  const Navigation = useNavigation()
  
  return (
    <View style={styles.container} >
      <Text  onPress={() => Navigation.navigate("announcement")}>원두 투자 시작해볼까요?</Text>
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
