import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  TextInput,
  TouchableOpacity,
  Image,
  StyleSheet,
  Alert,
} from 'react-native';
import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://10.125.51.52:8000/cwise/api/v2/',
});

const LoginScreen = ({ navigation }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);
  const [showPassword, setShowPassword] = useState(false);

  useEffect(() => {

    const loadSavedCredentials = async () => {
      const savedEmail = await AsyncStorage.getItem('email');
      const savedPassword = await AsyncStorage.getItem('password');
      if (savedEmail && savedPassword) {
        setEmail(savedEmail);
        setPassword(savedPassword);
        setRememberMe(true);
      }
    };
    loadSavedCredentials();
  }, []);

  const toggleShowPassword = () => {
    setShowPassword(!showPassword);
  };

  const handleLogin = async () => {
    if (email === '' || password === '') {
      Alert.alert('Error', 'Please enter email and password.');
      return;
    }

    try {
      const response = await api.post('/user/login', { email, password });
      const data = response.data;

      if (data.message === "Success") {
        setEmail('');
        setPassword('');
          
          await AsyncStorage.setItem('email', email);
          await AsyncStorage.setItem('password', password);
          await AsyncStorage.setItem('token', data.data.token);
        
        navigation.navigate('Pointage');
      } else {
        Alert.alert('Error', data.message);
      }
    } catch (error) {
      console.error('Error:', error);
      Alert.alert('Error', 'An error occurred. Please try again later.');
    }
  };

  return (
    <View style={styles.container}>
      <Image
        source={require('../assets/bk-view.png')}
        style={styles.background}
      />
      <Image
        source={require('../assets/ClockWise_Logo.png')}
        style={styles.logo}
      />
      <Text style={[styles.welcomeText, { color: 'rgba(0, 29, 46, 1)' }]}>Welcome</Text>
      <TextInput
        style={styles.input}
        placeholder="Email"
        keyboardType="email-address"
        autoCapitalize="none"
        value={email}
        onChangeText={setEmail}
      />
      <View style={styles.passwordInputContainer}>
        <TextInput
          style={styles.passwordInput}
          placeholder="Password"
          secureTextEntry={!showPassword}
          value={password}
          onChangeText={setPassword}
        />
        <TouchableOpacity
          style={styles.showPasswordButton}
          onPress={toggleShowPassword}
        >
          <Image
            source={showPassword ? require('../assets/eye-off.png') : require('../assets/eye.png')}
            style={styles.showPasswordIcon}
          />
        </TouchableOpacity>
      </View>

      <View style={styles.rememberMeContainer}>
        <TouchableOpacity
          style={styles.checkbox}
          onPress={() => setRememberMe(!rememberMe)}
        >
          {rememberMe && <View style={styles.checkedBox} />}
        </TouchableOpacity>
        <Text style={styles.rememberMeText}>Remember Me</Text>
      </View>
      <TouchableOpacity style={styles.loginButton} onPress={handleLogin}>
        <Text style={[styles.buttonText, { color: 'black' }]}>Log In</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.forgotPasswordButton}>
        <Text style={[styles.buttonText, { color: 'black' }]}>Forgot Password?</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  background: {
    position: 'absolute',
    width: '100%',
    height: '100%',
    resizeMode: 'cover',
  },
  logo: {
    width: 250,
    height: 250,
    marginTop: -100,
    marginBottom: 50,
    resizeMode: 'contain',
  },
  welcomeText: {
    fontWeight: 'bold',
    fontSize: 40,
    marginBottom: 50,
    marginTop: -60,
  },
  input: {
    width: '80%',
    height: 50,
    borderWidth: 1,
    borderColor: 'gray',
    borderRadius: 10,
    paddingHorizontal: 10,
    marginBottom: 10,
    backgroundColor: 'rgba(255, 255, 255, 0.8)',
  },
  loginButton: {
    width: '80%',
    backgroundColor: 'rgba(200, 236, 252, 100)',
    alignItems: 'center',
    justifyContent: 'center',
    height: 50,
    borderRadius: 10,
    marginBottom: 10,
    marginTop: 50,
    borderWidth: 1,
    borderColor: 'black',
  },
  forgotPasswordButton: {
    width: '80%',
    alignItems: 'center',
  },
  buttonText: {
    fontSize: 16,
  },
  rememberMeContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: 10,
  },
  checkbox: {
    width: 20,
    height: 20,
    borderRadius: 3,
    borderWidth: 1,
    borderColor: '#000',
    alignItems: 'center',
    justifyContent: 'center',
    marginRight: 10,
  },
  checkedBox: {
    width: 10,
    height: 10,
    backgroundColor: '#000',
  },
  rememberMeText: {
    fontSize: 16,
  },
  passwordInputContainer: {
    width: '80%',
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 10,
  },
  passwordInput: {
    flex: 1,
    height: 50,
    borderWidth: 1,
    borderColor: 'gray',
    borderRadius: 10,
    paddingHorizontal: 10,
    backgroundColor: 'rgba(255, 255, 255, 0.8)',
  },
  showPasswordButton: {
    padding: 10,
  },
  showPasswordIcon: {
    width: 24,
    height: 24,
  },
});

export default LoginScreen;
