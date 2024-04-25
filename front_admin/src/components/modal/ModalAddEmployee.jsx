import React, { useState } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import employeeService from "../service/employeeService";

const ModalForm = ({ showModal, handleClose }) => {
    const [error, setError] = useState('');
    const [formData, setFormData] = useState({
        firstname: '',
        lastname: '',
        email: '',
        weekWorkingHour: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await employeeService.registerEmployee(formData);
        }catch (error){
            setError('échec de création utilisateur.');
        }
        console.log('Formulaire soumis : ', formData);
        handleClose();
    };

    return (
        <Modal show={showModal} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Formulaire</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label>Prenom :</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Entrez votre nom"
                            name="firstname"
                            value={formData.firstname}
                            onChange={handleChange}
                        />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>nom :</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Entrez votre email"
                            name="lastname"
                            value={formData.lastname}
                            onChange={handleChange}
                        />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>email :</Form.Label>
                        <Form.Control
                            type="email"
                            placeholder="Entrez votre email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                        />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>heures contrat:</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Entrez votre email"
                            name="email"
                            value={formData.weekWorkingHour}
                            onChange={handleChange}
                        />
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Soumettre
                    </Button>
                </Form>
            </Modal.Body>
        </Modal>
    );
};

export default ModalForm;
