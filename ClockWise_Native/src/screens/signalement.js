import React, { useState } from 'react';
import { View, Text, Image, TouchableOpacity, TextInput, StyleSheet } from 'react-native';
import { useNavigation } from '@react-navigation/native'; // Importation de useNavigation

function SignalementScreen() {
  const [currentTime, setCurrentTime] = useState(new Date().toLocaleTimeString());
  const navigation = useNavigation(); // Utilisation de useNavigation pour obtenir l'objet de navigation

  const handleSignalementPress = () => {
    // Action à exécuter lors du clic sur "Signaler un problème"
  };

  const handleBackButtonPress = () => {
    navigation.navigate('Pointage'); 
  };

  const handleDecoPress = () => {
    navigation.navigate('Login'); 
  };

  return (
    <View style={styles.container}>
      {/* Image en haut */}
      <Image
        source={require('../assets/logo_clockwise.png')}
        style={styles.image}
      />
      {/* Prénom et Nom */}
      <Text style={[styles.name, { color: 'rgba(0, 29, 46, 1)'}]}>MATHEO CORZA</Text>
      <View style={styles.separator} />
      <Text style={[styles.signal, { color: 'rgba(0, 29, 46, 1)'}]}>PAGE DE SIGNALEMENT</Text>

      {/* Champ Objet */}
      <Text style={[styles.label, { color: 'rgba(0, 29, 46, 1)'}]}>Objet:</Text>
      <TextInput
        style={styles.input}
        placeholder="Entrez l'objet du signalement"
      />

      {/* Champ Commentaire */}
      <Text style={[styles.label, { color: 'rgba(0, 29, 46, 1)'}]}>Commentaire:</Text>
      <TextInput
        style={[styles.input, { height: 200 }]}
        placeholder="Entrez votre commentaire"
        multiline
      />

      {/* Boutons supplémentaires */}
      <View style={styles.additionalButtons}>
        <TouchableOpacity style={styles.additionalButton1} onPress={handleSignalementPress}>
          <Text style={styles.additionalButtonText}>Envoyer</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.additionalButton2} onPress={handleBackButtonPress}>
          <Text style={styles.additionalButtonText}>Retour</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.additionalButton3} onPress={handleDecoPress}>
          <Text style={styles.additionalButtonText}>Se déconnecter</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    paddingHorizontal: 50,
  },
  image: {
    width: 200,
    height: 150,
    resizeMode: 'contain',
    marginBottom: 20,
  },
  name: {
    fontSize: 40,
    marginVertical: -10,
    fontWeight: 'bold',
  },
  separator: {
    width: '55%',
    height: 3,
    backgroundColor: 'black',
    marginVertical: 15,
  },
  signal: {
    fontSize: 25,
    fontWeight: 'bold',
    marginBottom: 20,
    marginTop: 15,
  },
  label: {
    fontSize: 18,
    marginBottom: 10,
    color: 'black',
    fontWeight: 'bold',
  },
  input: {
    width: '100%',
    height: 40,
    borderWidth: 1,
    borderColor: 'gray',
    borderRadius: 5,
    paddingHorizontal: 10,
    marginBottom: 20,
    borderWidth: 2,
    borderColor: 'black', 
  },
  additionalButtons: {
    alignItems: 'center',
    width: 300,
    height: 250,
  },
  additionalButton1: {
    width: '100%',
    paddingVertical: 20,
    paddingHorizontal: 20,
    borderRadius: 10,
    borderWidth: 1,
    borderColor: 'black',
    marginBottom: 20,
    alignItems: 'center',
    backgroundColor: 'rgba(64, 145, 191, 1)',
    borderWidth: 2,
    borderColor: 'black', 
  },
  additionalButton2: {
    width: '100%',
    paddingVertical: 20,
    paddingHorizontal: 20,
    borderRadius: 10,
    borderWidth: 1,
    borderColor: 'black',
    marginBottom: 20,
    alignItems: 'center',
    backgroundColor: 'rgba(92, 205, 74, 1)',
    borderWidth: 2,
    borderColor: 'black', 
  },
  additionalButton3: {
    width: '100%',
    paddingVertical: 20,
    paddingHorizontal: 20,
    borderRadius: 10,
    borderWidth: 1,
    borderColor: 'black',
    marginBottom: 20,
    alignItems: 'center',
    backgroundColor: 'rgba(226, 33, 33, 1)',
    borderWidth: 2,
    borderColor: 'black', 
  },
  additionalButtonText: {
    fontSize: 18,
    fontWeight: 'bold',
    color: 'black',
  },
});

export default SignalementScreen;
