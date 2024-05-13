import { Text, View, StyleSheet, Pressable, Button } from "react-native";
import { useAccount, useBalance, useDisconnect } from "wagmi";
import { Ionicons } from '@expo/vector-icons';
import { useEffect, useState } from "react";
import Colors from "../../constants/Colors";
import { useDispatch, useSelector } from "react-redux";
import { setWalletModal } from "../../stores/mySlice";
import { useWeb3Modal } from "@web3modal/wagmi-react-native";

const MyWallet = () => {
  const isModalOpen = useSelector(state => state.myPageInfo.isWalletModalOpen);
  const dispatch = useDispatch();

  const { open } = useWeb3Modal();
  const { disconnect } = useDisconnect();

  const { address, isConnected, isDisconnected } = useAccount();
  const { data, isLoading } = useBalance({ address, chainId: "0x79f5", watch: true, address: "0x85c41a930ddEc0f37BAED79BEd3047Af190c4f98"});
  // const { data, isLoading } = useBalance({ address: "0x85c41a930ddEc0f37BAED79BEd3047Af190c4f98" });
  
  const modalHandler = () => {
    dispatch(setWalletModal(!isModalOpen));
  };
  
  const disconnectWallet = () => {
    disconnect();
    dispatch(setWalletModal(false));
  };
  
  useEffect(() => {
    console.log("토큰 조회 데이터", data)    
  }, [data])

  return (
    <View style={styles.container}>
      {
        isConnected ? 
        <>
          <View style={styles.topContainer}>
            <View style={styles.addressContainer}>
              <Ionicons name="wallet-outline" size={16} />
              <Text style={styles.addressText} numberOfLines={1} ellipsizeMode="tail">{address}</Text>
            </View>
            <Pressable onPress={modalHandler}>
              <Ionicons name="ellipsis-vertical-outline" size={16} />
            </Pressable>
            <View style={[styles.modal, {display: isModalOpen ? 'block' : 'none'}]}>
              <Pressable onPress={disconnectWallet}>
                <Text style={styles.modalMenuText}>연결 끊기</Text>
              </Pressable>
            </View>
          </View>
          <View style={styles.balanceContainer}>
            <Text style={styles.balanceText}>{data?.formatted}</Text>
            <Text style={styles.symbolText}>{data?.symbol}</Text>
          </View>
        </>
        :
        <Pressable onPress={() => open()}>
          <Text style={styles.connectText}>+ 전자 지갑 연동</Text>
        </Pressable>
      }
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
    gap: 16,
    minHeight: 100,
    justifyContent: 'center',
    alignItems: 'center'
  },
  topContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    gap: 16,
    position: 'relative'
  },
  addressContainer: {
    flexDirection: 'row',
    gap: 4,
    alignItems: 'center',
    flex: 1
  },
  addressText: { 
    fontSize: 12,
    fontFamily: 'pretendard-regular',
    flex: 1
  },
  balanceContainer: {
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
  },
  modal: {
    position: 'absolute',
    paddingVertical: 4,
    paddingHorizontal: 6,
    backgroundColor: 'white',
    borderColor: Colors.fontGray,
    borderWidth: 0.5,
    borderRadius: 5,
    right: 0,
    bottom: -32,
    width: '30%'
  },
  modalMenuText: {
    fontSize: 12,
    color: Colors.fontGray,
    textAlign: 'center'
  },
  connectText: {
    fontSize: 16,
    fontFamily: 'pretendard-medium',
    color: Colors.fontGray,
    textAlign: 'center'
  }
})