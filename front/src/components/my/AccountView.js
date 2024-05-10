import { useEffect } from "react";
import { Text, View, StyleSheet } from "react-native";
import { useAccount, useBalance } from "wagmi";

const AccountView = () => {
  const { address, isConnected } = useAccount();
  const { data, isLoading } = useBalance({ address });
  useEffect(() => {
    console.log(data)
  }, [data])

  return isConnected ? (
    data && (
      <View style={styles.container}>
        <Text style={styles.balanceText}>{data?.formatted}</Text>
        <Text style={styles.symbolText}>{data?.symbol}</Text>
      </View>
    )
  ) : null;
}

export default AccountView;

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    gap: 4,
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: 8
  },
  balanceText: {
    fontSize: 20,
    fontFamily: 'pretendard-regular'
  },
  symbolText: {
    fontSize: 16,
    fontFamily: 'pretendard-regular'
  }
});