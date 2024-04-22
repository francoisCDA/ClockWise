import React, { useState } from 'react';
import { View, Text, Image, TouchableOpacity, StyleSheet } from 'react-native';
import { useNavigation } from '@react-navigation/native';

function PointageScreen() {
  const [currentTime, setCurrentTime] = useState(new Date().toLocaleTimeString());
  const navigation = useNavigation();

  const handlePointerPress = () => {
    // Action à exécuter lors du clic sur le bouton "Pointer"
  };

  const handleSignalementPress = () => {
    navigation.navigate('Signalement'); 
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
      {/* Heure actuelle */}
      <Text style={[styles.time, { color: 'rgba(0, 29, 46, 1)'}]}>{currentTime}</Text>
      {/* Bouton "Pointer" */}
      <TouchableOpacity style={styles.pointerButton} onPress={handlePointerPress}>
        <View style={styles.pointerButtonInner}>
          <Image
            source={require('../assets/icon_deco.png')}
            style={styles.pointerIcon}
          />
          <Text style={styles.pointerText}>POINTAGE</Text>
        </View>
      </TouchableOpacity>
      {/* Barre de séparation */}
      <View style={styles.separator} />
      {/* Boutons supplémentaires */}
      <View style={styles.additionalButtons}>
        <TouchableOpacity style={styles.additionalButton1} onPress={handleSignalementPress}>
          <Text style={styles.additionalButtonText}>Signaler un problème</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.additionalButton2}>
          <Text style={styles.additionalButtonText}>Historique</Text>
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
  },
  image: {
    width: 200,
    height: 150,
    marginTop: 20,
    resizeMode: 'contain',
    marginTop: 0,
  },
  name: {
    fontSize: 40,
    marginVertical: 10,
    fontWeight: 'bold',
  },
  time: {
    fontSize: 25,
    marginBottom: 20,
    marginTop: 40,
  },
  pointerButton: {
    width: 220,
    height: 220,
    borderRadius: 20,
    backgroundColor: 'rgba(64, 145, 191, 1)',
    alignItems: 'center',
    justifyContent: 'center',
    borderWidth: 2,
    borderColor: 'black', 
  },
  pointerButtonInner: {
    alignItems: 'center',
  },
  pointerIcon: {
    width: 120,
    height: 120,
    resizeMode: 'contain',
  },
  pointerText: {
    fontSize: 25,
    color: 'white',
  },
  separator: {
    width: '55%',
    height: 3,
    backgroundColor: 'black',
    marginVertical: 35,
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
    backgroundColor: 'rgba(240, 233, 60, 1)',
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
  },
  additionalButtonText: {
    fontSize: 18,
    fontWeight: 'bold',
    color: 'black',
  },
});

export default PointageScreen;
