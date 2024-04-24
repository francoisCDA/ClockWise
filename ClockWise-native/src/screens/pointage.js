import React, { useState, useEffect } from 'react';
import { View, Text, Image, TouchableOpacity, StyleSheet } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import axios from 'axios';

function PointageScreen() {
  const [currentTime, setCurrentTime] = useState(new Date().toLocaleTimeString());
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [isWorking, setIsWorking] = useState(false);
  const navigation = useNavigation();

  useEffect(() => {
    const loadUserInfo = async () => {
      try {
        AsyncStorage.getItem('token').then(async value => {
          const response = await axios.get('http://10.125.51.54:8000/cwise/api/v2/employee/statut', {
            headers: {
              Authorization: `Bearer ${value}`,
            },
          });
          const userData = response.data;
          setFirstName(userData.data.firstname);
          setLastName(userData.data.lastname);
          setIsWorking(userData.data.isWorking);
        });
      } catch (error) {
        console.error('Erreur de chargement des informations utilisateur :', error);
      }
    };
    loadUserInfo();
  }, []);

  const handlePointerPress = async () => {
    try {
      AsyncStorage.getItem('token').then(async value => {
        const response = await axios.get('http://10.125.51.54:8000/cwise/api/v2/employee/stamp', {
          headers: {
            Authorization: `Bearer ${value}`,
          },
        });
        console.log(response.data);
        console.log('Pointage réussi');
        setIsWorking(!isWorking);
      });
    } catch (error) {
      console.error('Erreur de pointage :', error);
    }
  };

  const handleSignalementPress = () => {
    navigation.navigate('Signalement');
  };

  const handleHistoriquePress = () => {
    navigation.navigate('Historique'); 
  };

  const handleDecoPress = () => {
    navigation.navigate('Login');
  };

  return (
    <View style={styles.container}>
      <Image
        source={require('../assets/logo_clockwise.png')}
        style={styles.image}
      />
      <View style={styles.nameContainer}>
        <Text style={[styles.name, { color: 'black' }]}>{firstName} </Text>
        <Text style={[styles.name, { color: 'black' }]}>{lastName}</Text>
      </View>
      {isWorking ? (
        <Text style={[styles.workingStatus, { color: 'green' }]}>Vous êtes en train de travailler</Text>
      ) : (
        <Text style={[styles.workingStatus, { color: 'red' }]}>Vous n'êtes pas en train de travailler</Text>
      )}
      <TouchableOpacity style={styles.pointerButton} onPress={handlePointerPress}>
        <View style={styles.pointerButtonInner}>
          <Image
            source={require('../assets/icon_deco.png')}
            style={styles.pointerIcon}
          />
          <Text style={styles.pointerText}>POINTAGE</Text>
        </View>
      </TouchableOpacity>
      <View style={styles.separator} />
      <View style={styles.additionalButtons}>
        <TouchableOpacity style={styles.additionalButton1} onPress={handleSignalementPress}>
          <Text style={styles.additionalButtonText}>Signaler un problème</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.additionalButton2} onPress={handleHistoriquePress}>
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
  nameContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 10,
  },
  name: {
    fontSize: 40,
    marginVertical: 10,
    fontWeight: 'bold',
    color: 'black',
  },
  time: {
    fontSize: 25,
    marginBottom: 20,
    marginTop: 40,
    color: 'black',
  },
  workingStatus: {
    fontSize: 20,
    marginBottom: 20,
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
