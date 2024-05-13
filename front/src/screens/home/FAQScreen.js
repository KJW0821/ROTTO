import { useEffect, useState } from "react";
import { ScrollView, StyleSheet, View } from "react-native";
import { Text } from "react-native-paper";
import { getFAQList } from "../../utils/FAQApi";

const FAQ = () => {
  const [faqList, setFaqList] = useState([]);

  const getList = async () => {
    const res = await getFAQList();
    console.log(res);
    setFaqList(res.faqListDtos);
  };

  useEffect(() => {
    getList();
  }, []);

  return (
    <View style={styles.screen}>
      <Text style={styles.header}>FAQ</Text>
      {faqList &&
        faqList.map((item) => {
          return (
            <View key={item.faqCode}>
              <Text style={styles.title}>{item.title}</Text>
              <Text style={styles.content}>{item.content}</Text>
            </View>
          );
        })}
    </View>
  );
};

export default FAQ;

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
  }
});
