import { Text, View, Button } from "react-native";
import '@walletconnect/react-native-compat'
import { WalletConnectModal, useWalletConnectModal } from '@walletconnect/modal-react-native'

const projectId = '41c800331b3143bdaddeef0fdefb7852';

const providerMetadata = {
  name: 'rotto',
  description: '커피 STO 투자 증권 앱',
  url: 'exp://192.168.10.4:8081',
  icons: ['../../../assets/images/skyIcon.png'],
  redirect: {
    native: 'exp://192.168.10.4:8081',
    universal: 'YOUR_APP_UNIVERSAL_LINK.com'
  }
}

const MyScreen = () => {
  const { open, isConnected, provider, address } = useWalletConnectModal()

  const pressHandler = () => {
    if (isConnected) {
      provider.disconnect();
    } else {
      open()
    }
  }

  return (
    <View>
      <Text>이곳은 마이페이지입니다.</Text>
      <Button title="지갑 연동" onPress={pressHandler} />
      {
        isConnected &&
        <Text>{address[0]}</Text>
      }
      <WalletConnectModal projectId={projectId} providerMetadata={providerMetadata} />
    </View>
  );
};

export default MyScreen