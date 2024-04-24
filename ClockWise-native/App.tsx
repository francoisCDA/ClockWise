import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import Login from './src/screens/login';
import PointageScreen from './src/screens/pointage';
import Signalement from './src/screens/signalement'; 

const Stack = createStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Login" component={Login} options={{ headerShown: false }}/>
        <Stack.Screen name="Pointage" component={PointageScreen} options={{ headerShown: false }}/>
        <Stack.Screen name="Signalement" component={Signalement} options={{ headerShown: false }}/>
      </Stack.Navigator>
    </NavigationContainer>
  );
}

