import React from "react";
import axios from "axios";
import {ActivityIndicator, View, Alert} from "react-native";
import ShelterReport from "../components/ShelterReport";
import Config from "../Config";

class MainReportScreen extends React.Component {
    static navigationOptions = {
        title: "Centrala schroniska - raport"
    };

    state = {
        isLoading: true,
        errorMessage: null,
        report: null
    };

    componentDidMount() {
        axios.get(`${Config.AJAX_URL_BASE}/report?format=web`)
            .then(res => this.setState({isLoading: false, errorMessage: null, report: res.data}))
            .catch(err => {
                const newErrorMessage = err.response !== undefined ? err.response.data : "Brak połączenia z serwerem";

                this.setState({isLoading: false, errorMessage: newErrorMessage, report: null});
                Alert.alert("Błąd pobierania raportu o schronisku", newErrorMessage);
            });
    }

    render() {
        if(this.state.isLoading) {
            return (
                <View style={{flex: 1, justifyContent: "center", alignItems: "center"}}>
                    <ActivityIndicator size="large" />
                </View>
            );
        } else {
            return (
                <View style={{margin: 20}}>
                    {this.state.errorMessage !== null ? this.state.errorMessage : <ShelterReport report={this.state.report} />}
                </View>
            );
        }
    }
}

export default MainReportScreen;
