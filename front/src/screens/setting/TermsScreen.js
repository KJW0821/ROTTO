import { View, Text, ScrollView, StyleSheet } from 'react-native';
import SettingTopBar from '../../components/setting/SettingTopBar';
import { useEffect, useState } from 'react';
import { getTerms } from '../../utils/settingApi';

const TermsScreen = ({navigation}) => {
  const [terms, setTerms] = useState();

  useEffect(() => {
    const getTermsData = async () => {
      const res = await getTerms();
      setTerms(res.content);
    };

    getTermsData();
  }, [navigation])

  return (
    <View style={styles.container}>
      <SettingTopBar title="이용 약관" navigation={navigation} />
      {
        terms &&
        <ScrollView style={styles.contentContainer}>
          <Text>{terms}</Text>
        </ScrollView>
      }
    </View>
  )
}

export default TermsScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
    alignItems: 'center'
  },
  contentContainer: {
    width: '82%',
    paddingHorizontal: 16
  }
});