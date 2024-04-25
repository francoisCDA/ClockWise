import logo from './logo.svg';
import './App.css';
import Signin from "./components/auth/Signin";
import 'bootstrap/dist/css/bootstrap.min.css';
import Home from "./components/clockwise/Home";
import {Outlet} from "react-router-dom";
import {useState} from "react";
import employeeService from "./components/service/employeeService";

function App() {
    const [employees, setEmployees] = useState([]);

    useEffect(() => {
        const fetchEmployees = async () => {
            try {
                const response = await employeeService.getAllEmployees();
                setEmployees(response.data); // Supposons que response.data est une liste d'employés
            } catch (error) {
                console.error('Error fetching employees:', error);
            }
        };

        fetchEmployees();
    }, []);
  return (
    <>
        <Signin />
        <Outlet />
    </>
  );
}

export default App;
