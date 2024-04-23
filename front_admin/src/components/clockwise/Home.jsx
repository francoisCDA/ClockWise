import HeaderClockwise from "../header/HeaderClockwise";
import Table from "react-bootstrap/Table";
import {Container} from "react-bootstrap";
import Button from "react-bootstrap/Button";

function Home(){
    return(
        <>
            <HeaderClockwise />
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
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <Button variant="outline-info"> <span>Details</span></Button>
                            <Button variant="outline-warning"><span>Edit</span></Button>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </Table>
            </Container>

        </>
    )
}
export default Home;