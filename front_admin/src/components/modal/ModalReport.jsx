import React, { useState } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import employeeService from "../service/employeeService";

const ReportModal = ({ showModal, handleClose }) => {
    const [comment, setComment] = useState('');
    const [title, setTitle] = useState('');
    const [error, setError] = useState('');


    const handleChange = (e) => {
        setComment(e.target.value);
        setTitle(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await employeeService.registerReport(title, comment);
        } catch (error) {
            setError('échec création rapport')
        }
        console.log('Commentaire soumis : ', comment);
        // Fermez la modal après la soumission du commentaire
        handleClose();
    };

    return (
        <Modal show={showModal} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Ajouter un commentaire au rapport</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form.Group>
                    <Form.Label>Titre :</Form.Label>
                    <Form.Control
                        as="text"
                        rows={5}
                        placeholder="Entrez votre titre de rapport ici"
                        value={title}
                        onChange={handleChange}
                    />
                </Form.Group>
                <Form.Group>
                    <Form.Label>Commentaire :</Form.Label>
                    <Form.Control
                        as="textarea"
                        rows={5}
                        placeholder="Entrez votre commentaire ici"
                        value={comment}
                        onChange={handleChange}
                    />
                </Form.Group>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Annuler
                </Button>
                <Button variant="primary" onClick={handleSubmit}>
                    Soumettre
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default ReportModal;
