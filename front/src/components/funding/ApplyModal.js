import { View, Pressable, Text, StyleSheet, Modal, TextInput } from 'react-native';
import Colors from '../../constants/Colors';
import { useDispatch, useSelector } from 'react-redux';
import { setApplyModal } from '../../stores/fundingSlice';
import CustomButton from '../common/CustomButton';

const ApplyModal = () => {
  const dispatch = useDispatch();
  const modalVisible = useSelector(state => state.fundingInfo.isApplyModalOpen);
  const fundingData = useSelector(state => state.fundingInfo.fundingData);

  return (
    <Modal
      animationType='fade'
      transparent={true}
      visible={modalVisible}
      onRequestClose={() => dispatch(setApplyModal(false))}          
    >  
      <Pressable style={styles.modalBack} onPress={() => dispatch(setApplyModal(false))}>
        <View style={styles.modal}>
          <View style={styles.applyContainer}>
            <Text style={styles.title}>신청 수량</Text>
            <View style={styles.inputContainer}>
              <TextInput 
                style={styles.inputText}
              />
              <Text style={styles.infoText}> / {fundingData.limitNum} ROTTO</Text>
            </View>
            <CustomButton>신청</CustomButton>
          </View>
          <View style={styles.line} />
          <Pressable onPress={() => dispatch(setApplyModal(false))}>
            <Text style={styles.modalCloseMenu}>닫기</Text>
          </Pressable>
        </View>
      </Pressable>
    </Modal>
  )
}

export default ApplyModal;

const styles = StyleSheet.create({
  modalBack : {
    justifyContent: 'flex-end',
    flexGrow: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.25)'
  },
  modal: {
    backgroundColor: 'white',
    borderTopRightRadius: 20,
    borderTopLeftRadius: 20,
    alignItems: 'center'
  },
  line: {
    height: 1,
    borderTopColor: Colors.fontGray,
    borderWidth: 0.5,
    width: '100%'
  },
  modalCloseMenu: {
    textAlign: 'center',
    fontSize: 14,
    fontFamily: 'pretendard-medium',
    paddingVertical: 12,
    color: Colors.fontGray
  },
  applyContainer: {
    width: '85%',
    paddingVertical: 20,
    gap: 16
  },
  title: {
    fontSize: 16,
    fontFamily: 'pretendard-medium'
  },
  inputContainer: {
    flexDirection: 'row',
    alignItems: 'center'
  },
  inputText: {
    borderBottomColor: Colors.fontGray,
    borderBottomWidth: 1,
    fontSize: 16,
    fontFamily: 'pretendard-medium'
  },
  infoText: {
    fontSize: 16,
    fontFamily: 'pretendard-medium',
    color: Colors.fontGray
  }
});