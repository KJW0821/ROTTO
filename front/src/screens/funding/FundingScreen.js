import { Text, View, ScrollView, Pressable, StyleSheet } from "react-native";
import MyHeader from "../../components/common/MyHeader";
import Colors from "../../constants/Colors";
import { Ionicons } from "@expo/vector-icons";
import FilterButton from "../../components/funding/FilterButton";
import FilterBar from "../../components/funding/FilterBar";
import FundingList from "../../components/funding/FundingList";

const FundingScreen = ({navigation}) => {
  
  return (
    <MyHeader>
      <FilterBar />
      <FundingList navigation={navigation} />
    </MyHeader>
  )
}

export default FundingScreen;

const styles = StyleSheet.create({
  
});
