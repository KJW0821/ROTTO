import { Text, View, StyleSheet } from "react-native";
import '@walletconnect/react-native-compat'
import { W3mButton, W3mNetworkButton, Web3Modal } from '@web3modal/wagmi-react-native'
import AccountView from "./AccountView";

const MyWallet = () => {

  return (
    <View style={styles.container}>
      <W3mButton 
        label="connect"
        loadingLabel="Connecting..."
      />
      <AccountView />
    </View>
  );
};

export default MyWallet;

const styles = StyleSheet.create({
  container: {
    width: '90%',
    backgroundColor: 'white',
    borderRadius: 10,
    padding: 18,
    gap: 16
  }
})