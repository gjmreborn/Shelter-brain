import React from "react";
import {createStackNavigator, createAppContainer} from "react-navigation";
import MainReportScreen from "./screens/MainReportScreen";

export default createAppContainer(createStackNavigator({
  MainReportScreen: {screen: MainReportScreen}
}));
