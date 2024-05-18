import { View, StyleSheet, TouchableOpacity } from "react-native";
import MyHeader from "../../components/common/MyHeader";
import MyWallet from "../../components/my/MyWallet";
import MyAccount from "../../components/my/MyAccount";
import { useDispatch, useSelector } from "react-redux";
import { setDisconnectModal, setWalletModal } from "../../stores/mySlice";
import { useCallback, useEffect } from "react";
import { useFocusEffect } from "@react-navigation/native";
import InvestmentList from "../../components/my/InvestmentList";

const MyScreen = ({navigation}) => {
  const dispatch = useDispatch();

  useFocusEffect(
    useCallback(() => {
      return () => {
        dispatch(setDisconnectModal(false));
      }
    }, [navigation])
  );

  return (
    <MyHeader>
      <View style={styles.container}>
        <MyAccount navigation={navigation} />
        <MyWallet />
        <InvestmentList navigation={navigation} />
      </View>
    </MyHeader>
  );
};

export default MyScreen;

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    marginTop: 28,
    gap: 20,
    flex: 1
  }
})