import { useEffect, useState } from "react"
import { StyleSheet, Text, View } from "react-native"
import { getFarmTOP10 } from "../../utils/farmAPi"

const FarmTOP10 = () => {
    const [farmsTOP, setFarmsTOP] = useState([])

    const getList = async () => {
        const res = await getFarmTOP10()
        setFarmsTOP(res.farms)
    }
    
    useEffect(() => {
        getList()
    }, [])
    return <View style={styles.container}>
        {farmsTOP && farmsTOP.map(item => {
            return <View key={item.farmCode}>
                <Text>{item.farmName} - {item.beanName}</Text>
            </View>
        })}
    </View>
}

export default FarmTOP10

const styles = StyleSheet.create({
    container:{
        flex: 1,
        margin: 25
    }
})