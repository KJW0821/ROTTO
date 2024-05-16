import { useEffect, useState } from "react";
import {
  FlatList,
  Image,
  ScrollView,
  StyleSheet,
  Text,
  View,
  useWindowDimensions,
} from "react-native";
import { getFarmDetail } from "../../utils/farmAPi";
import StackHeader from "../../components/common/StackHeader";
import { addLike, removeLike } from "../../utils/FarmILikeApi";
import Colors from "../../constants/Colors";

const FarmScreen = ({ route }) => {
  const { width, height } = useWindowDimensions();
  const [farm, setFarm] = useState(null);
  // const farmImagesCount = Array.from({ length: 10 }, (_, i) => i + 1);
  const farmImagesCount = Array.from({ length: 10 }, (_, i) =>
    (i + 1).toString().padStart(3, "0")
  );

  const getDetail = async () => {
    const res = await getFarmDetail(route.params.farmCode);
    setFarm(res);
    console.log(res);
  };

  useEffect(() => {
    getDetail();
  }, []);

  const handlePressHeart = () => {
    farm.isLiked ? removeLike(farm.farmCode) : addLike(farm.farmCode);
    setFarm((prevFarm) => ({ ...prevFarm, isLiked: !farm.isLiked }));
  };

  const renderFarmImage = (itemData) => {
    return (
      <Image source={{ uri: `${S3URL}/farm_img/1/farm${itemData.item}.jpg` }} />
    );
  };

  return (
    <>
      {farm && (
        <ScrollView style={styles.screen}>
          <StackHeader
            screenName="farmList"
            title={farm.farmName}
            detail={true}
            isLiked={farm.isLiked}
            onPressHeart={handlePressHeart}
          />
          <View style={styles.container}>
            <View style={styles.rowConatainer}>
              <Image
                source={{
                  uri: `${process.env.EXPO_PUBLIC_S3URL}/farm_img/1/${farm.farmLogoPath}`,
                }}
                style={styles.farmLogo}
              />
              <Text style={styles.title}>{farm.farmName}</Text>
            </View>
            <View style={styles.content}>
              <Text>
                안녕하세요. 농부 후안입니다. 저는 바리스타가 아니라 농장
                주인인데요..
              </Text>
            </View>
          </View>
          <View style={styles.container}>
            <Text style={styles.title}>농장 정보</Text>
            <View style={styles.content}>
              <View style={{ flexDirection: "row", marginBottom: 30 }}>
                <View style={{ flex: 1 }}>
                  <Text style={styles.infoTitle}>지난 수익률</Text>
                  <Text style={styles.infoContent}>+43.38%</Text>
                </View>
                <View style={{ flex: 1 }}>
                  <Text style={styles.infoTitle}>재배 원두</Text>
                  <Text style={styles.infoContent}>{farm.beanName}</Text>
                </View>
              </View>
              <View style={{ flexDirection: "row" }}>
                <View style={{ flex: 1 }}>
                  <Text style={styles.infoTitle}>농장 시작일</Text>
                  <Text style={styles.infoContent}>
                    {farm ? farm.farmStartedDate.split("T")[0] : ""}
                  </Text>
                </View>
                <View style={{ flex: 1 }}>
                  <Text style={styles.infoTitle}>규모</Text>
                  <Text style={styles.infoContent}>{farm.farmScale} ha</Text>
                </View>
              </View>
            </View>
          </View>
          <View style={styles.container}>
            <Text style={styles.title}>수상내역</Text>
            <View style={styles.content}>
              <Text>{farm.awardHistory}</Text>
            </View>
          </View>
          <View style={styles.container}>
            <Text style={styles.title}>주소</Text>
            <View style={styles.content}>
              <Text>{farm.farmAddress}</Text>
            </View>
          </View>
          <View style={styles.imageContainer}>
            <Text style={styles.imageTitle}>농장 둘러보기</Text>
            <View style={styles.imageContent}>
              {farmImagesCount.map((item) => (
                <Image
                  source={{ uri: `${S3URL}/farm_img/1/farm${item}.jpg` }}
                  style={{
                    width: (width / 100) * 32,
                    height: (width / 100) * 32,
                  }

                }
                />
              ))}
            </View>
          </View>
        </ScrollView>
      )}
    </>
  );
};

export default FarmScreen;

const styles = StyleSheet.create({
  screen: {
    flex: 1,
    backgroundColor: Colors.bgGray,
  },
  info: {
    padding: 25,
  },
  container: {
    backgroundColor: "white",
    padding: 25,
    marginBottom: 10,
  },
  imageContainer: {
    backgroundColor: "white",
    paddingTop: 25,
    marginBottom: 10,
  },
  imageTitle: {
    fontFamily: "pretendard-extraBold",
    fontSize: 20,
    paddingHorizontal: 25,
  },
  imageContent: {
    marginHorizontal: 5,
    marginTop: 20,
    flexDirection: "row",
    flexWrap: "wrap",
    justifyContent: "space-between",
  },
  rowConatainer: {
    flexDirection: "row",
    alignItems: "center",
  },
  title: {
    fontFamily: "pretendard-extraBold",
    fontSize: 20,
  },
  content: {
    marginTop: 20,
  },
  farmLogo: {
    height: 30,
    width: 30,
    resizeMode: "cover",
    borderRadius: 15,
    marginRight: 10,
  },
  infoTitle: {
    fontFamily: "pretendard-extraBold",
    fontSize: 15,
  },
  infoContent: {
    fontFamily: "pretendard-semiBold",
    fontSize: 15,
  },
});
