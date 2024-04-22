import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import {Container} from "react-bootstrap";
import Image from 'react-bootstrap/Image';

function Signin() {
    return (
        <Container>
            <Row>
                <Col md={6}>
                    <div className="signin-content">
                        <div className="signin-header">
                            <h2>Connexion</h2>
                            <Image src={require('../../assets/Logo.png')} alt="Logo" />
                        </div>
                        <Form>
                            <Form.Group className="mb-3" controlId="formBasicEmail">
                                <Form.Label>Email address:</Form.Label>
                                <Form.Control type="email" placeholder="Enter email" />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formBasicPassword">
                                <Form.Label>Password:</Form.Label>
                                <Form.Control type="password" placeholder="Password" />
                            </Form.Group>
                            <Button variant="primary" type="submit">
                                Connexion
                            </Button>
                        </Form>
                    </div>
                </Col>
                <Col md={6}>
                    <div className="signin-image">
                        <Image src={require('../../assets/Home.jpg')} alt="Signin Image" />
                    </div>
                </Col>
            </Row>
        </Container>
    );
}

export default Signin;