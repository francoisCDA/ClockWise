import {Container, Stack} from "react-bootstrap";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Image from "react-bootstrap/Image";
import 'react-date-range/dist/styles.css'; // main style file
import 'react-date-range/dist/theme/default.css'; // theme css file
import {useState} from 'react'
import { DateRange } from 'react-date-range';
import Button from "react-bootstrap/Button";

function HeaderClockwise(){
    const [state, setState] = useState([
        {
            startDate: new Date(),
            endDate: null,
            key: 'selection'
        }
    ]);
    return(
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
                        <Button variant="warning">faire rapport</Button>
                        <Button variant={"success"}>Afficher employés</Button>
                    </Stack>
                </Col>
            </Row>
        </Container>
    )
}

export default HeaderClockwise;