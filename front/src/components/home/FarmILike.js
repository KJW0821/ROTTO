import React, { useEffect, useState, useRef } from "react";
import {
  ScrollView,
  StyleSheet,
  View,
  Dimensions,
  TouchableOpacity,
  Image,
  ImageBackground,
} from "react-native";
import { useNavigation } from "@react-navigation/native";
import { Text } from "react-native-paper";
import { getFarmILikeList } from "../../utils/FarmILikeApi";

const { width } = Dimensions.get("window");

const FarmILike = () => {
  const [farmILikeList, setfarmILikeList] = useState([]);

  const getList = async () => {
    const res = await getFarmILikeList();
    console.log(res.data.farms);
    setfarmILikeList(res.data.farms);
  };

  useEffect(() => {
    getList();
  }, []); 
  const Navigation = useNavigation();
  const [currentIndex, setCurrentIndex] = useState(0);
  const scrollViewRef = useRef();

  const handleScroll = (event) => {
    const contentOffsetX = event.nativeEvent.contentOffset.x;
    const newIndex = Math.round(contentOffsetX / width);
    setCurrentIndex(newIndex);
  };

  // return (
  //   <View style={styles.screen}>
  //     <Text style={styles.header}>FAQ</Text>
  //     {farmILikeList &&
  //       farmILikeList.map((item) => {
  //         return (
  //           <View key={item.farmCode}>
  //             <Text style={styles.title}>{item.farmName}</Text>
  //             <Text style={styles.content}>{item.beanName}</Text>
  //           </View>
  //         );
  //       })}
  //   </View>
  // );

  return (
    <View style={styles.screen}>
      <View
        style={[
          {
            flexDirection: "row",
            alignItems: "center",
            marginHorizontal: 10,
            justifyContent: "space-between",
          },
        ]}
      >
        <Text style={styles.header}>관심 농장</Text>
        <Text style={styles.more}>더보기</Text>
      </View>

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
          {farmILikeList &&
            farmILikeList.map((item) => {
              return (
                <TouchableOpacity
                  key={item.farmCode}
                  onPress={() => Navigation.navigate("announcement")}
                >
                  <ImageBackground
                    source={require("../../../assets/images/farmfarm.png")}
                    style={{
                      width: width,
                      height: 150,
                      justifyContent: "center",
                      alignItems: "center",
                    }}
                  >
                    <Text style={styles.title}> {item.farmName}</Text>
                    <Text style={styles.content}>{item.beanName}</Text>
                  </ImageBackground>
                </TouchableOpacity>
              );
            })}
        </ScrollView>
      </View>
    </View>
  );
};

/*
{ {farmILikeList &&
        farmILikeList.map((item) => {
          return (
            <View key={item.farmName}>
              <Text style={styles.farmName}>{item.farmName}</Text>
              <Text style={styles.beanName}>{item.beanName}</Text>
            </View>
          );
        })} } 
*/
export default FarmILike;

const styles = StyleSheet.create({
  screen: {
    flex: 1,
    backgroundColor: "white",
  },
  header: {
    fontFamily: "pretendard-extraBold",
    fontSize: 23,
    paddingBottom: 20,
  },
  more: {
    fontFamily: "pretendard-regular",
    fontSize: 16,
    paddingBottom: 20,
  },
  title: {
    fontFamily: "pretendard-bold",
    fontSize: 18,
    marginTop: 20,
  },
  content: {
    marginTop: 5,
  },
  carouselContainer: {
    // 새로운 스타일
    marginTop: 10,
    position: "relative",
  },
  scrollViewStyle: {
    width: "100%",
  },
  item: {
    width: width - 20,
    borderRadius: 15,
    margin: 20,
    height: 200,
    justifyContent: "center",
    alignItems: "center",
  },
  text: {
    fontFamily: "pretendard-extraBold",
    fontSize: 30,
    color: "white",
  },
});
