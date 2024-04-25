import Table from "react-bootstrap/Table";
import {Container, NavLink, Stack} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Image from "react-bootstrap/Image";
import {DateRange} from "react-date-range";
import {useState} from "react";
import ModalAddEmployee from "../modal/ModalAddEmployee";

function Home({employees}){
    const [state, setState] = useState([
        {
            startDate: new Date(),
            endDate: null,
            key: 'selection'
        }
    ]);

    const [showModal, setShowModal] = useState(false);

    const openModal = () => {
        setShowModal(true);
    };

    const closeModal = () => {
        setShowModal(false);
    };
    return(
        <>
            <Container>
                <Row>
                    <Col md={4}>
                        <div className="header-content">
                            <div className="header-pic">
                                <Image src={require('../../assets/Logo.png')} height={200} width={200} alt="logo" />
                            </div>
                        </div>
                    </Col>
                    <Col md={4}>
                        <div className="header-calendar">
                            <DateRange
                                editableDateInputs={true}
                                onChange={item => setState([item.selection])}
                                moveRangeOnFirstSelection={false}
                                ranges={state}
                            />
                        </div>
                    </Col>
                    <Col md={4}>
                        <Stack gap={3} className="col-md-5 mx-auto">
                            <Button variant="danger">Se déconnecter</Button>
                            <Button variant="warning" onClick={openModal}>ajouter employee</Button>
                            <ModalAddEmployee showModal={showModal} handleClose={closeModal} />
                            <NavLink to={"/list"}>
                                <Button variant={"success"}>Afficher rapports</Button>
                            </NavLink>
                        </Stack>
                    </Col>
                </Row>
            </Container>
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
                        </tr>

                    ))}
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