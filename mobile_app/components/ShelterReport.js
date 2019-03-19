import React from "react";
import Animal from "./Animal";
import { FlatList, Text, View, ScrollView } from "react-native";
import HorizontalLine from "./HorizontalLine";

const ShelterReport = props => {
    getAnimalsFromReportEntity = report => {
        return report.animals.map(animal => Object.assign({}, animal, {key: animal.id}));
    };

    const report = props.report;

    return (
        <View>
            <ScrollView>
                <Text style={{textAlign: "center", fontSize: 20}}>Raport schroniska</Text>

                <HorizontalLine />

                <Text style={{textAlign: "center", fontWeight: "bold"}}>Zwierzęta na stanie</Text>
                {report.animals.length !== 0 ?
                    <FlatList data={this.getAnimalsFromReportEntity(report)} renderItem={elem => <Animal animal={elem.item} />} /> :
                    <Text style={{fontSize: 40, textDecorationLine: "underline"}}>Brak zwierząt</Text>}
                
                <HorizontalLine />
                <View style={{flex: 1, flexDirection: "row", flexWrap: "wrap", alignItems: "flex-start"}}>
                    <Text style={{width: "50%"}}><Text style={{fontWeight: "bold", textDecorationLine: "underline"}}>{report.occupiedPlaces} / {report.maxAnimals}</Text> zajętych miejsc</Text>
                    <Text style={{width: "50%"}}>{report.shelterStatus === "FULL" ? "Schronisko pełne" : "Są jeszcze wolne miejsca"}</Text>
                </View>
            </ScrollView>
        </View>
    );
}

export default ShelterReport;
