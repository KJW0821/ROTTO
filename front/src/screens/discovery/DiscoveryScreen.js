import { ScrollView, StyleSheet, Text, View } from "react-native";
import MyHeader from "../../components/common/MyHeader";
import FarmPreview from "../../components/discovery/FarmPreview";
import CoffeeInfo from "../../components/discovery/CoffeeInfo";

const DiscoveryScreen = () => {
  return (
    <>
      <MyHeader>
        <ScrollView style={styles.container}>
          <View style={styles.component}>
            <Text style={styles.title}>농장</Text>
            <FarmPreview />
            <Text style={styles.title}>원두소개</Text>
          </View>
          <View style={styles.component}>
            <CoffeeInfo />
          </View>
        </ScrollView>
      </MyHeader>
    </>
  );
};

export default DiscoveryScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: 10,
    paddingVertical: 25,
    borderTopRightRadius: 15,
    borderTopLeftRadius: 15,
    backgroundColor: "white",
  },
  title: {
    paddingHorizontal: 25,
    fontFamily: "pretendard-bold",
    fontSize: 20,
  },
  component: {
    flex: 1,
  },
});
