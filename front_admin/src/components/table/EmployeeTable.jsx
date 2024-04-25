import {Container} from "react-bootstrap";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";


const EmployeeTable = ({employees}) =>{
    return(
        <Container fluid="md">
            <Table striped bordered hover responsive="md">
                <thead>
                <tr>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Email</th>
                    <th>Nombre d'heure(s)</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {employees.map((employee) => (
                    <tr key={employee.id}>
                        <td>{employee.lastname}</td>
                        <td>{employee.firstname}</td>
                        <td>{employee.email}</td>
                        <td>{employee.weekWorkingHour}</td>
                        <td>
                            <Button variant="outline-info"> <span>Details</span></Button>
                            <Button variant="outline-warning"><span>Edit</span></Button>
                        </td>
                    </tr>

                ))}
                </tbody>
            </Table>
        </Container>
    )
}

export default EmployeeTable;