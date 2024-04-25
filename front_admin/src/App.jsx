import logo from './logo.svg';
import './App.css';
import Signin from "./components/auth/Signin";
import 'bootstrap/dist/css/bootstrap.min.css';
import Home from "./components/clockwise/Home";
import {Outlet} from "react-router-dom";
import {useEffect, useState} from "react";
import employeeService from "./components/service/employeeService";

function App() {
  return (
    <>
        <Signin />
        <Outlet />
    </>
  );
}

export default App;
