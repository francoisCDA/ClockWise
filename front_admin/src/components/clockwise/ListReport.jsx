import {Container, NavLink, Stack} from "react-bootstrap";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Image from "react-bootstrap/Image";
import {DateRange} from "react-date-range";
import Button from "react-bootstrap/Button";
import Table from "react-bootstrap/Table";
import {useState} from "react";
import ModalReport from "../modal/ModalReport";


function Listreport(){
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
                            <Button variant="warning" onClick={openModal}>faire rapport</Button>
                            <ModalReport showModal={showModal} handleClose={closeModal} />
                            <NavLink to={"/home"}>
                                <Button variant={"success"}>Afficher employés</Button>
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

export default Listreport;