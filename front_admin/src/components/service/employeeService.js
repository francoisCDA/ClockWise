import api from "./api";
import {authHeader} from "./authHeader";



const registerEmployee = (email, password, firstname, lastname, weekWorkingHour ) => {

    const employee = {
        email: email, //string
        password: password, //string
        firstname: firstname, //string
        lastname: lastname, // string
        weekWorkingHour: weekWorkingHour // float
    }
    const token = JSON.parse(localStorage.getItem('token'));

    const authHeader = { Authorization: 'Bearer ' + token };

    return api.post('/admin/employee', employee, { headers: authHeader } );
}

const getAllEmployees = () => {
    return api.get('/employee', {headers: authHeader()})
};

const getEmployeeId = () => {
    return api.get('/employee/{id}', {headers: authHeader()})
}


export default {registerEmployee, getAllEmployees};