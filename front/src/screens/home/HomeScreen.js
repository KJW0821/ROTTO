import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  Button,
  Platform,
  Image,
} from "react-native";

import MyHeader from "../../components/common/MyHeader";
import MyBanner from "../../components/home/MyBanner";
import MyDeposit from "../../components/home/MyDeposit";
import FaqBanner from "../../components/home/FaqBanner";
import FarmILike from "../../components/home/FarmILike";
import FarmNews from "../../components/home/FarmNews";

const HomeScreen = ({ navigation }) => {

  return (
    <MyHeader>
      <ScrollView style={styles.container}>
        <MyBanner />
        <MyDeposit />
        <FaqBanner />
        <View style={styles.infoContainer}>
          <FarmILike />
          <FarmNews />
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
    padding: 15,
    borderTopRightRadius: 15,
    borderTopLeftRadius: 15,
    backgroundColor: "white",
  },
});
