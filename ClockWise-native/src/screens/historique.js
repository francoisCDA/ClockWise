import React from 'react';
import { View, Text, Button, StyleSheet, Image, TouchableOpacit } from 'react-native';


const Historique = ({ navigation }) => {
  const handleBackPress = () => {
    navigation.goBack(); 
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Historique des Pointages</Text>
      <Button title="Retour" onPress={handleBackPress} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  
});

export default Historique;
