import { useState } from "react";
import {
  Image,
  ImageBackground,
  Platform,
  StyleSheet,
  Text,
  View,
  ViewBase,
  useWindowDimensions,
} from "react-native";
import { Ionicons } from "@expo/vector-icons";
import Colors from "../../constants/Colors";
import { useNavigation } from "@react-navigation/native";

const FarmPreview = () => {
  const [selectImage, setSelectImage] = useState(0);
  const { width, height } = useWindowDimensions();
  const Navigation = useNavigation();

  return (
    <View style={styles.container}>
      <View style={styles.farmImage}>
        <ImageBackground
          source={
            selectImage === 0
              ? require("../../../assets/images/discovery/coffeefarm.png")
              : selectImage === 1
                ? require("../../../assets/images/discovery/coffeebeans.png")
                : require("../../../assets/images/discovery/coffeefarm2.png")
          }
          style={{ resizeMode: "stretch", height: width * 3 / 4, width: width}}
        >
          <View style={styles.textContainer}>
            <Text style={styles.title}>
              {selectImage === 0
                ? "케냐"
                : selectImage === 1
                  ? "에티오피아"
                  : "브라질"}
            </Text>
            <Text
              style={styles.buttonText}
              onPress={() => Navigation.navigate("farmList")}
            >
              농장 조회
            </Text>
          </View>
          <Ionicons
            name="caret-forward"
            size={40}
            color="white"
            style={styles.nextButton}
            onPress={() =>
              setSelectImage(selectImage < 2 ? selectImage + 1 : 0)
            }
          />
        </ImageBackground>
      </View>
    </View>
  );
};

export default FarmPreview;

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  farmImage: {
    marginTop: 10,
    alignItems: "center",
  },
  textContainer: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
  },
  buttonText: {
    color: "white",
    fontFamily: "pretendard-bold",
    fontSize: 20,
    textShadowColor: "rgba(59, 59, 59, 0.5)", // 그림자 색상
    textShadowOffset: { width: 2, height: 2 }, // 그림자 위치
    textShadowRadius: 5, // 그림자 반경
    margin: 10,
  },
  title: {
    margin: 10,
    color: "white",
    fontFamily: "pretendard-extraBold",
    fontSize: 30,
    textShadowColor: "rgba(0, 0, 0, 0.5)", // 그림자 색상
    textShadowOffset: { width: 2, height: 2 }, // 그림자 위치
    textShadowRadius: 5, // 그림자 반경
  },
  nextButton:{
    flex: 1,
    left: 360,
    top: 60
  }
});
