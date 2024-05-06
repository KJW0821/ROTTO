import { FlatList, StyleSheet, Text, View } from "react-native";
import { useNavigation } from "@react-navigation/native";
import { useEffect, useState } from "react";
import { Ionicons } from "@expo/vector-icons";
import { getAlertList } from "../../utils/AlertApi";

import StackHeader from "../../components/common/StackHeader";
import Colors from "../../constants/Colors";

const AlertListScreen = () => {
  const Navigation = useNavigation();
  const [alerts, setalerts] = useState([]);

  useEffect(() => {
    const getList = async () => {
      const res = await getAlertList();
      setalerts(res.alerts); // 상태 업데이트
    };
    getList();
  }, []);

  const renderAlertList = (itemData) => {
    const date = itemData.item.createTime.split("T")[0];
  
    return (
      <View style={styles.container}>
        <View>
          <Text style={styles.title}>{itemData.item.title}</Text>
          <Text style={styles.date}>
            {date.split("-")[0]}년 {date.split("-")[1]}월 {date.split("-")[2]}일
          </Text>
        </View>
        <View>
          <Ionicons
            name="chevron-forward"
            size={24}
            color="black"
            onPress={() =>
              Navigation.navigate("alert", {
                alertCode: itemData.item.alertCode,
              })
            }
          />
        </View>
      </View>
    );
  };

  return (
    <>
      <StackHeader screenName="home" />
      <View style={styles.screen}>
        <Text style={styles.header}>알림</Text>
        <FlatList
          data={alerts}
          keyExtractor={(item) => {
            item.alertCode;
          }}
          renderItem={renderAlertList}
        />
      </View>
    </>
  );
};

export default AlertListScreen;

const styles = StyleSheet.create({
  screen: {
    flex: 1,
    backgroundColor: "white",
    paddingHorizontal: 25,
  },
  header: {
    fontFamily: "pretendard-extraBold",
    fontSize: 23,
    paddingHorizontal: 10,
    paddingBottom: 20,
  },
  container: {
    flex: 1,
    marginVertical: 10,
    flexDirection: "row",
    justifyContent: "space-between",
  },
  title: {
    fontFamily: "pretendard-bold",
    fontSize: 15,
  },
  date: {
    fontFamily: "pretendard-regular",
    fontSize: 13,
    color: Colors.fontGray,
  },
});
