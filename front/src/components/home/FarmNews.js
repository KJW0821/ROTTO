import { useEffect, useState } from "react";
import { ScrollView, StyleSheet, View } from "react-native";
import { Text } from "react-native-paper";
import { getFarmNewsList } from "../../utils/FarmNewsApi";

const FarmNews = () => {
  const [farmNewsList, setfarmNewsList] = useState([]);

  const getList = async () => {
    try{
      const res = await getFarmNewsList();
      console.log(res);
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
        farmNewsList.map((item, index) => index < 3 && (
            <View key={item.newsCode} style={styles.itemStyle}>
              <View style={{flexShrink: 1}}>
                <Text style={styles.title}>{item.title}</Text>
              </View>
              <View style={{backgroundColor: "grey", width: 60, height: 60, borderRadius: 15, marginLeft: 10}}>
              </View>
            </View>
          ))
        }
      <View style={{flexDirection:"row", alignItems: "center", justifyContent:"center", margin: 20}}>
        <Text style={{fontFamily: "pretendard-bold", fontSize: 18, color: "#778F8C"}}>더 보기</Text>
      </View>
    </View>
  );
  
};

export default FarmNews;

const styles = StyleSheet.create({
  screen: {
    flex: 1,
    margin: 10,
    backgroundColor: "white",
  },
  header: {
    margin: 10,
    fontFamily: "pretendard-extraBold",
    fontSize: 23,
  },
  itemStyle: {
    margin: 10,
    flexDirection: "row"
  },
  title: {
    fontFamily: "pretendard-bold",
    fontSize: 16,
    marginRight: 20
  },
});
