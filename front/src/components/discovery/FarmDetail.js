import { useEffect } from "react";
import {
  Image,
  Pressable,
  StyleSheet,
  Text,
  View,
  useWindowDimensions,
} from "react-native";
import Colors from "../../constants/Colors";
import { useNavigation } from "@react-navigation/native";

const FarmDetail = ({ data }) => {
  const Navigation = useNavigation()
  const { width, height } = useWindowDimensions();

  useEffect(() => {
    console.log(data);
  }, []);

  return (
    <Pressable
      onPress={() => {
        Navigation.navigate("farm", {
          farmCode: data.farmCode,
        });
      }}
      style={[styles.container, { width: width * 0.9, height: 100 }]}
    >
      <Image source={{ uri: data.farmLogoPath }} />
      <Text style={styles.title}>{data.farmName}</Text>
      <Text>{data.beanName}</Text>
    </Pressable>
  );
};

export default FarmDetail;

const styles = StyleSheet.create({
  container: {
    borderRadius: 15,
    borderColor: Colors.borderGray,
    borderWidth: 2,
    marginBottom: 15,
  },
  title: {
    color: "black",
  },
});
