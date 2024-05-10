import { useEffect, useState } from "react";
import { ScrollView, StyleSheet, View } from "react-native";
import { Text } from "react-native-paper";
import { getFarmNewsList } from "../../utils/FarmNewsApi";

const FarmNews = () => {
  const [farmNewsList, setfarmNewsList] = useState([]);

  const getList = async () => {
    try{
      const res = await getFarmNewsList();
      setfarmNewsList(res);
    }
    catch(err){
      console.error('FarmNews getList error: ', error);
    }
  };
  
  useEffect(() => {
    getList();
  }, []);

  return (
    <View style={styles.screen}>
      <Text style={styles.header}>농장 소식</Text>
      {farmNewsList &&
        farmNewsList.map((item) => {
          return (
            <View key={item.newsCode}>
              <Text style={styles.title}>{item.title}</Text>
              <Text style={styles.content}>{item.content}</Text>
            </View>
          );
        })}
    </View>
  );
};

export default FarmNews;

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
  title: {
    fontFamily: "pretendard-bold",
    fontSize: 18,
    marginTop: 20
  },
  content: {
    marginTop: 5
  },

  carouselContainer: {
    // 새로운 스타일
    marginTop: 10,
    position: "relative",
  },
  scrollViewStyle: {
    width: "100%",
  },
  text: {
    fontFamily: "pretendard-extraBold",
    fontSize: 30,
    color: "white",
  },
  indicatorContainer: {
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

});
