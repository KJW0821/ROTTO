import { View, TextInput, StyleSheet } from "react-native";
import SettingTopBar from "../../components/setting/SettingTopBar";
import CustomButton from "../../components/common/CustomButton";
import Colors from "../../constants/Colors";

const InquiryCreateScreen = ({navigation}) => {
  return (
    <View style={styles.container}>
      <SettingTopBar title='문의 작성' navigation={navigation} />
      <View style={styles.contentContainer}>
        <TextInput 
          placeholder="제목을 입력해주세요.(20자 이내)"
          placeholderTextColor={Colors.fontGray}
          maxLength={20}
          style={[styles.inputBox, { paddingVertical: 5 }]}
        />
        <TextInput 
          placeholder="내용을 입력해주세요.(1,000자 이내)"
          placeholderTextColor={Colors.fontGray}
          maxLength={1000}
          multiline={true}
          numberOfLines={7}
          style={[styles.inputBox, { textAlignVertical: 'top' }]}
        />
        <CustomButton>작성 완료</CustomButton>
      </View>
    </View>
  )
}

export default InquiryCreateScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
    alignItems: 'center'
  },
  contentContainer: {
    width: '82%',
    gap: 12,
    marginVertical: 12
  },
  inputBox: {
    borderRadius: 10,
    backgroundColor: '#ECECEC',
    width: '100%',
    fontFamily: 'pretendard-medium',
    fontSize: 12,
    padding: 10
  }
});