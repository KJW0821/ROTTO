import { useEffect, useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import { getFarmDetail } from "../../utils/farmAPi";
import StackHeader from "../../components/common/StackHeader";
import { addLike, removeLike } from "../../utils/FarmILikeApi";

const FarmScreen = ({ route }) => {
  const [farm, setFarm] = useState({});

  const getDetail = async () => {
    const res = await getFarmDetail(route.params.farmCode);
    setFarm(res);
  };

  useEffect(() => {
    getDetail();
  }, []);

  const handlePressHeart = () => {
    farm.isLiked ? removeLike(farm.farmCode) : addLike(farm.farmCode)
    setFarm((prevFarm) => ({...prevFarm, isLiked : !farm.isLiked}))
  }

  return (
    <>
      <View style={styles.screen}>
        <StackHeader screenName="farmList" title={farm.farmName} detail={true} isLiked={farm.isLiked} onPressHeart={handlePressHeart}/>
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
