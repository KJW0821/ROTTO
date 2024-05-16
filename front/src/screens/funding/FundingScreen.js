import { StyleSheet } from "react-native";
import MyHeader from "../../components/common/MyHeader";
import FilterBar from "../../components/funding/FilterBar";
import FundingList from "../../components/funding/FundingList";
import FilterModal from "../../components/funding/FilterModal";

const FundingScreen = ({navigation}) => {
  
  return (
    <MyHeader>
      <FilterBar />
      <FundingList navigation={navigation} />
      <FilterModal />
    </MyHeader>
  )
}

export default FundingScreen;

const styles = StyleSheet.create({
  
});
