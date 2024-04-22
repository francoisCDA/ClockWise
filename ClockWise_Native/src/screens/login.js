import React, { useState } from 'react';
import {
  View,
  Text,
  TextInput,
  TouchableOpacity,
  Image,
  StyleSheet,
} from 'react-native';

function LoginScreen({ navigation }) {
  const [rememberMe, setRememberMe] = useState(false);

  const toggleRememberMe = () => {
    setRememberMe(!rememberMe);
  };

  const handleLogin = () => {
    navigation.navigate('Pointage');
  };

  return (
    <View style={styles.container}>
      {/* Background */}
      <Image
        source={require('../assets/bk-view.png')}
        style={styles.background}
      />
      {/* Logo */}
      <Image
        source={require('../assets/ClockWise_Logo.png')}
        style={styles.logo}
      />
      {/* Welcome Text */}
      <Text style={[styles.welcomeText, { color: 'rgba(0, 29, 46, 1)'}]}>Welcome</Text>
      {/* Email Input */}
      <TextInput
        style={styles.input}
        placeholder="Email"
        keyboardType="email-address"
        autoCapitalize="none"
      />
      {/* Password Input */}
      <TextInput
        style={styles.input}
        placeholder="Password"
        secureTextEntry
      />
      {/* Remember Me Button */}
      <TouchableOpacity style={styles.rememberButton} onPress={toggleRememberMe}>
        <Text style={[styles.buttonText, { color: rememberMe ? 'black' : 'black' }]}>
          {rememberMe ? '✓ Remember Me' : 'Remember Me'}
        </Text>
      </TouchableOpacity>
      {/* Login Button */}
      <TouchableOpacity style={styles.loginButton} onPress={handleLogin}>
        <Text style={[styles.buttonText, { color: 'black' }]}>Log In</Text>
      </TouchableOpacity>
      {/* Forgot Password Button */}
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
    marginBottom: 50,
    resizeMode: 'contain',
  },
  welcomeText: {
    fontWeight: 'bold', 
    fontSize: 40,
    marginBottom: 20,
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
  rememberButton: {
    width: '80%',
    alignItems: 'center',
    marginBottom: 50,
  },
  loginButton: {
    width: '80%',
    backgroundColor: 'rgba(200, 236, 252, 100)',
    alignItems: 'center',
    justifyContent: 'center',
    height: 50,
    borderRadius: 10,
    marginBottom: 10,
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
});

export default LoginScreen;
