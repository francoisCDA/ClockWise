import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

import LoginScreen from './src/screens/login';
import PointageScreen from './src/screens/pointage';

const Stack = createStackNavigator();

function AppNavigator() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Login">
        <Stack.Screen name="Login" component={LoginScreen} />
        <Stack.Screen name="Pointage" component={PointageScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

export default AppNavigator;
