import React, { useRef, useState } from "react";
import {
  Image,
  Pressable,
  StyleSheet,
  Text,
  View,
  useWindowDimensions,
} from "react-native";
import { COFFEE_BEAN_INFO } from "../../../assets/data/bean-data";
import Colors from "../../constants/Colors";

const CoffeeInfo = () => {
  const { width, height } = useWindowDimensions();
  const [selectedItem, setSelectedItem] = useState(null);
  // const [imagePath, setImagePath] = useState(null);
  // const imagePath = useRef("")
  let imagePath = COFFEE_BEAN_INFO[0].imageUrl;

  const updateSelect = (id) => {
    if (id === selectedItem) {
      setSelectedItem(null);
      // setImagePath(null);
    } else {
      // setImagePath(COFFEE_BEAN_INFO[id - 1].imageUrl);
      imagePath = COFFEE_BEAN_INFO[id - 1].imageUrl;
      console.log(imagePath);
      setSelectedItem(id);
    }
  };

  const renderBeanInfo = (itemData) => {
    const isSelected = selectedItem === itemData.item.id;
    return (
      <Pressable
        style={[
          styles.item,
          isSelected && { borderColor: Colors.bgOrg }, // 선택된 아이템의 테두리 색상 변경
        ]}
        onPress={() => updateSelect(itemData.item.id)}
      >
        <Text>{itemData.item.name}</Text>
      </Pressable>
    );
  };

  return (
    <View style={styles.container}>
      <Image
          source={require("../../../assets/images/discovery/coffeebean11.png")}
          style={{ width: width, height: 250, resizeMode: "cover"}}
        />
      <View style={styles.flatListContainer}>
        {COFFEE_BEAN_INFO.map((item) => (
          <View key={item.id} style={styles.flatListItemContainer}>
            {renderBeanInfo({ item })}
          </View>
        ))}
      </View>

      <View style={styles.description}>
        
        {selectedItem && imagePath != "" && (
          <>
            {/* <Image
              source={{uri : imagePath}}
              style={{ width: "100%", height: 200, resizeMode: "cover" }}
            /> */}
            <Text style={styles.descriptionFont}>{COFFEE_BEAN_INFO[selectedItem - 1].description}</Text>
          </>
        )}
      </View>
    </View>
  );
};

export default CoffeeInfo;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    marginTop: 10,
  },
  flatListContainer: {
    marginTop: 20,
    flexDirection: "row",
    flexWrap: "wrap",
    justifyContent: "center",
    alignItems: "center",
  },
  flatListItemContainer: {
    width: 130,
    height: 40,
    justifyContent: "center",
    alignItems: "center",
    
    margin: 5,
  },
  item: {
    width: "100%",
    height: "100%",
    justifyContent: "center",
    alignItems: "center",
    borderWidth: 2,
    borderColor: Colors.borderGray,
  },
  description: {
    flex: 1,
    // height: 400,
    width: "95%",
    alignItems: 'center',
    marginTop: 10
  },
  descriptionFont:{
    fontFamily: "pretendard-regular",
    fontSize: 15
  }
});

//   return (
//     <View style={styles.container}>
//       <FlatList
//         data={COFFEE_BEAN_INFO}
//         numColumns={3}
//         renderItem={renderBeanInfo}
//         keyExtractor={(item) => item.id}
//       />
//       <View style={styles.description}>
//         {selectedItem && (
//           <>
//             <Image
//               // source={require("../../../assets/images/discovery/coffeebean1.png")}
//               // source={{uri : imagePath}}
//               style={{ width: "100%", resizeMode: "stretch" }}
//             />
//             <Text>{COFFEE_BEAN_INFO[selectedItem - 1].description}</Text>
//           </>
//         )}
//       </View>
//     </View>
//   );
// };
