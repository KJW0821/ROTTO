import { View, Text, Pressable, Switch, StyleSheet } from 'react-native';
import Colors from '../../constants/Colors';
import { useEffect, useState } from 'react';
import ToggleButton from '../../components/common/ToggleButton';
import SettingService from '../../utils/setting';
import { logout } from '../../utils/userApi';
import TokenService from '../../utils/token';

const SettingScreen = ({navigation}) => {
  const [isCheckedBio, setIsCheckedBio] = useState(false);
  const [isCheckedPush, setIsCheckedPush] = useState(false);
  
  const toggleBio = async () => {
    await SettingService.setBiometricEnabled(!isCheckedBio);
    setIsCheckedBio(!isCheckedBio);
  };

  const togglePush = async () => {
    await SettingService.setPushEnabled(!isCheckedPush);
    setIsCheckedPush(!isCheckedPush);
  };

  useEffect(() => {
    const getBiometricEnabled = async () => {
      const isEnabled = await SettingService.getBiometricEnabled();
      setIsCheckedBio(isEnabled);
    };

    const getPushEnabled = async () => {
      const isEnabled = await SettingService.getPushEnabled();
      setIsCheckedPush(isEnabled);
    };

    const loadInitialData = async () => {
      await Promise.all([
        getBiometricEnabled(),
        getPushEnabled()
      ]);
    };

    loadInitialData();
  }, []);

  const logoutHandler = async () => {
    await logout();
    await TokenService.clearAllData();
    navigation.reset({
      index: 0,
      routes: [{ name: "Onboarding" }],
    });
  };

  return (
    <View style={styles.screen}>
      <View style={styles.topBar}>
        <Text style={styles.title}>설정</Text>
      </View>
      <View style={styles.container}>
        <Text style={styles.subTitle}>보안</Text>
        <Pressable style={styles.menuContainer}>
          <Text style={styles.menuText}>비밀번호 변경</Text>
        </Pressable>
        <Pressable style={styles.menuContainer}>
          <Text style={styles.menuText}>간편 비밀번호 변경</Text>
        </Pressable>
        <View style={styles.menuContainer}>
          <Text style={styles.menuText}>생체 인증 등록/해제</Text>
          <ToggleButton 
            onToggle={toggleBio}
            isOn={isCheckedBio}
          />
        </View>
        <View style={styles.line} />
        <View style={styles.menuContainer}>
          <Text style={styles.menuText}>버전 정보</Text>
          <Text style={styles.menuText}>v1.0.0</Text>
        </View>
        <View style={styles.menuContainer}>
          <Text style={styles.menuText}>알림 설정</Text>
          <ToggleButton 
            onToggle={togglePush}
            isOn={isCheckedPush}
          />
        </View>
        <View style={styles.line} />
        <Pressable style={styles.menuContainer} onPress={() => navigation.navigate('inquiry')}>
          <Text style={styles.menuText}>문의하기</Text>
        </Pressable>
        <Pressable style={styles.menuContainer}>
          <Text style={styles.menuText}>이용약관</Text>
        </Pressable>
        <View style={styles.line} />
        <Pressable onPress={logoutHandler} style={styles.menuContainer}>
          <Text style={[styles.menuText, { color: 'red' }]}>로그아웃</Text>
        </Pressable>
        <Pressable style={styles.menuContainer}>
          <Text style={[styles.menuText, { color: '#888888' }]}>탈퇴하기</Text>
        </Pressable>
      </View>
    </View>
  )
}

export default SettingScreen;

const styles = StyleSheet.create({
  screen: {
    flex: 1,
    backgroundColor: 'white',
    alignItems: 'center'
  },
  topBar: {
    width: '100%',
    height: '12%',
    justifyContent: 'center',
    alignItems: 'center'
  },
  container: {
    width: '82%',
    gap: 12
  },
  title: {
    fontFamily: 'pretendard-medium',
    fontSize: 20
  },
  subTitle: {
    fontFamily: 'pretendard-medium',
    fontSize: 18,
    color: Colors.fontGray
  },
  menuContainer: {
    width: '100%',
    justifyContent: 'space-between',
    flexDirection: 'row',
    alignItems: 'center'
  },
  menuText: {
    fontFamily: 'pretendard-medium',
    fontSize: 16,
  },
  line: {
    width: '100%',
    borderBottomColor: Colors.fontGray,
    borderBottomWidth: 0.5
  }
});