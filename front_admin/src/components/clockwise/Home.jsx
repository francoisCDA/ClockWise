import Table from "react-bootstrap/Table";
import {Container, Stack} from "react-bootstrap";
import {NavLink, useNavigate} from "react-router-dom";
import Button from "react-bootstrap/Button";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Image from "react-bootstrap/Image";
import {DateRange} from "react-date-range";
import {useEffect, useState} from "react";
import ModalAddEmployee from "../modal/ModalAddEmployee";
import employeeService from "../service/employeeService";
import EmployeeTable from "../table/EmployeeTable";
import authService from "../service/authService";

function Home(){
    const navigate = useNavigate();
    const [employees, setEmployees] = useState([]);
    const [state, setState] = useState([
        {
            startDate: new Date(),
            endDate: null,
            key: 'selection'
        }
    ]);

    const handleLogout = () => {
        authService.logout();
        navigate('/'); // Rediriger vers la page de connexion
    };

    const [showModal, setShowModal] = useState(false);

    const openModal = () => {
        setShowModal(true);
    };

    const closeModal = () => {
        setShowModal(false);
    };

    useEffect(() => {
        const fetchEmployees = async () => {
            try {
                const response = await employeeService.getAllEmployees();
                setEmployees(response.data);
            } catch (error) {
                console.error('Error fetching employees:', error);
            }
        };

        fetchEmployees();
    }, []);

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
                            <Button variant="danger" onClick={handleLogout}>Se déconnecter</Button>
                            <Button variant="warning" onClick={openModal}>ajouter employee</Button>
                            <ModalAddEmployee showModal={showModal} handleClose={closeModal} />
                            <NavLink to={"/list"}>
                                <Button variant={"success"}>Afficher rapports</Button>
                            </NavLink>
                        </Stack>
                    </Col>
                </Row>
            </Container>
            <div>
                <h3>Liste des employés</h3>
                <EmployeeTable employees={employees} />
            </div>
        </>
    )
}
export default Home;