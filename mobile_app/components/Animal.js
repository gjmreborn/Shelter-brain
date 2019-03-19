import React from "react";
import DateTimeUtils from "../utils/DateTimeUtils";
import {View, Image, Text} from "react-native";

const Animal = props => {
    const animal = props.animal;

    return (
        <View style={{flex: 1, justifyContent: "center", alignItems: "center", borderWidth: 3, borderColor: "#808080", borderRadius: 50, borderStyle: "solid"}}>
            <Image source={{uri: `data:image/png;base64,${animal.image}`}} style={{width: 200, height: 200}} />
            <Text style={{textDecorationLine: "underline"}}>{animal.name}</Text>
            <Text><Text style={{fontWeight: "bold"}}>Płeć</Text>: {animal.gender === "FEMALE" ? "żeńska" : "męska"}</Text>
            <Text><Text style={{fontWeight: "bold"}}>Wiek</Text>: {animal.age}</Text>
            <Text><Text style={{fontWeight: "bold"}}>Data dołączenia</Text>: {DateTimeUtils.defaultFormatDateTime(animal.dateOfAdd)}</Text>
        </View>
    );
}

export default Animal;
