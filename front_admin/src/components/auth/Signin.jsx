import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import {Container} from "react-bootstrap";
import Image from 'react-bootstrap/Image';
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import authService from "../service/authService";

function Signin() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleLogin =  async (e) =>{
        e.preventDefault();
        console.log(email, password);
        try {
            await authService.login(email, password);
            navigate('/home');
        } catch (error){
            setError('échec de la connexion. veuillez vérifier les données')
        }
    };
    return (
        <Container>
            <Row>
                <Col md={6}>
                    <div className="signin-content">
                        <div className="signin-header">
                            <h2>Log In</h2>
                            {error && <p className="text-danger">{error}</p> }
                            <Image src={require('../../assets/Logo.png')} alt="Logo" />
                        </div>
                        <Form onSubmit={handleLogin}>
                            <Form.Group className="mb-3">
                                <Form.Label>Email address:</Form.Label>
                                <Form.Control type="text" placeholder="Enter email" id="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>Password:</Form.Label>
                                <Form.Control type="password" placeholder="Password" id="password" value={password} onChange={(e) => setPassword(e.target.value)}/>
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