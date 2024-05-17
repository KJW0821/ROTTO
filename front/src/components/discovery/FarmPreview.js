import { useRef, useState } from "react";
import {
  Dimensions,
  Image,
  ImageBackground,
  Platform,
  ScrollView,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
  ViewBase,
  useWindowDimensions,
} from "react-native";
import { Ionicons } from "@expo/vector-icons";
import Colors from "../../constants/Colors";
import { useNavigation } from "@react-navigation/native";

const { width } = Dimensions.get("window");

const FarmPreview = () => {
  const [selectImage, setSelectImage] = useState(0);
  const Navigation = useNavigation();

  const [currentIndex, setCurrentIndex] = useState(0);
  const scrollViewRef = useRef();

  const handleScroll = (event) => {
    const contentOffsetX = event.nativeEvent.contentOffset.x;
    const newIndex = Math.round(contentOffsetX / width);
    setCurrentIndex(newIndex);
  };

  return (
    <View style={styles.container}>
      <View style={styles.carouselContainer}>
        <ScrollView
          horizontal={true}
          pagingEnabled={true}
          showsHorizontalScrollIndicator={false}
          style={styles.scrollViewStyle}
          onScroll={handleScroll}
          ref={scrollViewRef}
          scrollEventThrottle={16}
        >
          <View style={styles.item}>
            <Image
              source={require("../../../assets/images/coffee_farm.png")}
              style={{ resizeMode: "stretch" }}
            />
          </View>
          <View style={styles.item}>
            <Image
              source={require("../../../assets/images/discovery/coffeebeans.png")}
              style={{ height: "100%", width: "100%"}}
            />
          </View>
          <View style={styles.item}>
          <Image
              source={require("../../../assets/images/discovery/coffeefarm2.png")}
              style={{ height: "100%", width: "100%"}}
            />
          </View>
        </ScrollView>
        <View style={styles.indicatorContainer}>
          {[...Array(3).keys()].map((index) => (
            <View
              key={index}
              style={[
                styles.indicator,
                currentIndex === index && styles.activeIndicator,
              ]}
            />
          ))}
        </View>
      </View>
      {/* <View style={styles.farmImage}>
        <ImageBackground
          source={
            selectImage === 0
              ? require("../../../assets/images/discovery/coffeefarm.png")
              : selectImage === 1
              ? require("../../../assets/images/discovery/coffeebeans.png")
              : require("../../../assets/images/discovery/coffeefarm2.png")
          }
          style={{
            resizeMode: "stretch",
            height: (width * 3) / 4,
            width: width,
          }}
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
      </View> */}
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
  nextButton: {
    flex: 1,
    left: 360,
    top: 60,
  },
  carouselContainer: {
    // 새로운 스타일
    flex: 1,
    backgroundColor: "#cac721b6",
    marginTop: 10,
    alignItems: "center",
    justifyContent: "space-between",
  },
  scrollViewStyle: {
    width: "100%",
  },
  indicatorContainer: {
    marginLeft: 10,
    position: "absolute",
    bottom: 10,
    left: 20, // 왼쪽 아래 정렬을 위해 변경
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center",
  },
  indicator: {
    width: 10,
    height: 10,
    borderRadius: 5,
    backgroundColor: "lightgray",
    margin: 5,
  },
  activeIndicator: {
    backgroundColor: "white",
  },
  item: {
    alignItems: "center",
    width: width - 50,
    height: ((width - 50) * 3) / 4,
    backgroundColor: "#cf5b5b",
  },
});
