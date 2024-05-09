import { useEffect, useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import { getFarmDetail } from "../../utils/farmAPi";
import StackHeader from "../../components/common/StackHeader";

const FarmScreen = ({ route }) => {
  const [farm, setFarm] = useState({
    awardHistory: "공통, 특화, 자율 1, 1,1등",
    beanGrade: 1,
    beanName: "파나마 게이샤",
    farmAddress: "부울경",
    farmCode: 11,
    farmImgPath: null,
    farmLogoPath: null,
    farmName: "경훈농장",
    farmScale: 100,
    farmStartedDate: "1993-03-21T00:00:00",
    isLiked: false,
  });

  const getDetail = async () => {
    const res = await getFarmDetail(route.params.farmCode);
    setFarm(res);
  };

  useEffect(() => {
    getDetail();
  }, []);

  return (
    <>
      <View style={styles.screen}>
        <StackHeader screenName="farmList" />
        <View style={styles.info}>
        <Text>{farm.awardHistory}</Text>
        <Text>{farm.beanName}</Text>
        <Text>{farm.farmAddress}</Text>
        <Text>{farm.farmName}</Text>
        <Text>{farm.farmStartedDate}</Text>
        </View>
      </View>
    </>
  );
};

export default FarmScreen;

const styles = StyleSheet.create({
  screen: {
    flex: 1,
    backgroundColor: "white",
  },
  info: {
    padding: 25
  }
});
