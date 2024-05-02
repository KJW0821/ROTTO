import { View, Text, StyleSheet, ScrollView } from "react-native";
import MyHeader from "../components/common/MyHeader";
import MyBanner from "../components/home/MyBanner";
import MyDeposit from "../components/home/MyDeposit";

const HomeScreen = () => {
  return (
    <MyHeader>
      <ScrollView style={styles.container}>
        <MyBanner />
        <MyDeposit />
        <View style={styles.infoContainer}>
          <Text>이것은 홈페이지</Text>
        </View>
      </ScrollView>
    </MyHeader>
  );
};

export default HomeScreen;

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    infoContainer: {
        flex: 1,
        marginTop: 25,
        padding: 25,
        borderTopRightRadius: 15,
        borderTopLeftRadius: 15,
        backgroundColor: 'white',
        height: 1000
    }
});
