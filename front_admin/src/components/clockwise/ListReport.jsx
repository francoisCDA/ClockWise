import {Container, Stack} from "react-bootstrap";
import {NavLink, useNavigate} from "react-router-dom";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Image from "react-bootstrap/Image";
import {DateRange} from "react-date-range";
import Button from "react-bootstrap/Button";
import {useState} from "react";
import ModalReport from "../modal/ModalReport";
import authService from "../service/authService";


function Listreport(){
    const navigate = useNavigate();
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
    const handleLogout = () => {
        authService.logout();
        //localStorage.removeItem('user'); // Supprimer l'utilisateur du localStorage pour "déconnecter"
        navigate('/'); // Rediriger vers la page de connexion
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
        </>
    )

}

export default Listreport;