import { View, Text, Pressable, StyleSheet } from 'react-native';
import Colors from '../../constants/Colors';

const CustomButton = ({children, onPress}) => {
  return (
    <View style={styles.buttonOuterContainer}>
      <Pressable 
        style={({pressed}) => pressed ? [styles.buttonInnerContainer, styles.pressed] : styles.buttonInnerContainer} 
        onPress={onPress} 
      >
        <Text style={styles.buttonText}>{children}</Text>
      </Pressable>
    </View>
  )
}

export default CustomButton;

const styles = StyleSheet.create({
  buttonOuterContainer: {
    width: '100%',
    borderRadius: 5,
    overflow: 'hidden',
    height: 32
  },
  buttonInnerContainer: {
    backgroundColor: Colors.btnBlue,
    elevation: 2,
    height: '100%',
    justifyContent: 'center'
  },
  buttonText: {
    color: 'white',
    textAlign: 'center',
    marginBottom: 2,
    fontSize: 12,
    fontFamily: 'pretendard-bold'
  },
  pressed: {
    opacity: 0.75
  }
})