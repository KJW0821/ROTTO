import { useNavigation } from "@react-navigation/native";
import React, { useState, useRef } from "react";
import {
  ScrollView,
  View,
  Text,
  Dimensions,
  StyleSheet,
  Image,
  TouchableOpacity,
} from "react-native";

const { width } = Dimensions.get("window");

const FaqBanner = () => {
  const Navigation = useNavigation();
  const [currentIndex, setCurrentIndex] = useState(0);
  const scrollViewRef = useRef();

  return (
    <View style={styles.FAQContainer}>
      {/* <Text
        style={[
            styles.header,
            {
                marginLeft:25,
            },
          ]}
        >FAQ</Text> */}
      <TouchableOpacity onPress={() => Navigation.navigate("faqscreen")}>
        <View
          style={[
            styles.item,
            {
              backgroundColor: "#4E4E4E",
              flexDirection: "row",
              alignItems: "center",
              justifyContent: "space-between",
              height: 100,
            },
          ]}
        >
          <Image
            source={require("../../../assets/images/farmfarm.png")}
            style={{ width: 90, height: 90, resizeMode: "contain", flex: 1 }}
          />
          <View
          style={{flex: 2}}
          >
            <Text style={[styles.textHeader]}>투자공시</Text>
            <Text style={[styles.text]}>청약 신청 방법은 어떻게 되나요?</Text>
          </View>
        </View>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  header: {
    fontFamily: "pretendard-extraBold",
    fontSize: 23,
    color: "white",
  },
  FAQContainer: {
    marginTop: 10,
    position: "relative",
  },
  item: {
    width: width - 40,
    borderRadius: 20,
    marginTop: 5,
    marginHorizontal: 20,
    height: 200,
    justifyContent: "center",
    alignItems: "center",
  },
  textHeader: {
    fontFamily: "pretendard",
    fontSize: 16,
    color: "white",
  },
  text: {
    fontFamily: "pretendard-bold",
    fontSize: 18,
    color: "white",
  },
});

export default FaqBanner;
