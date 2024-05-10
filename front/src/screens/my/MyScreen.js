import { View, StyleSheet } from "react-native";
import MyHeader from "../../components/common/MyHeader";
import MyWallet from "../../components/my/MyWallet";
import '@walletconnect/react-native-compat'
import { WagmiConfig } from 'wagmi'
import { mainnet, polygon, arbitrum } from 'viem/chains'
import { Web3Modal, createWeb3Modal, defaultWagmiConfig } from '@web3modal/wagmi-react-native';

const projectId = '41c800331b3143bdaddeef0fdefb7852';

const metadata = {
  name: 'rotto',
  description: '커피 STO 투자 증권 앱',
  url: 'exp://192.168.30.140:8081',
  icons: ['../../../assets/images/skyIcon.png'],
  redirect: {
    native: 'exp://192.168.30.140:8081',
    universal: 'YOUR_APP_UNIVERSAL_LINK.com'
  }
};

const chains = [mainnet, polygon, arbitrum];

const wagmiConfig = defaultWagmiConfig({ chains, projectId, metadata });

createWeb3Modal({
  projectId,
  chains,
  wagmiConfig,
  enableAnalytics: true
});

const MyScreen = () => {

  return (
    <WagmiConfig config={wagmiConfig}>
      <MyHeader>
        <View style={styles.container}>
          <MyWallet />
        </View>
        <Web3Modal />
      </MyHeader>
    </WagmiConfig>
  );
};

export default MyScreen;

const styles = StyleSheet.create({
  container: {
    alignItems: 'center'
  }
})