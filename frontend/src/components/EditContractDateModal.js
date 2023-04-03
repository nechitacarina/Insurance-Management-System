import axios from "axios";
import { useEffect, useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import authHeader from "../services/auth-header";

function EditContractDateModal({ contract }) {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => {
    setShow(true);
    return axios
      .get("http://localhost:8090/api/contracts/" + contract.id, { headers: authHeader() })
      .then((response) => {
        setEffectiveDate(response.data.effectiveDate);
        setExpirationDate(response.data.expirationDate);
      });
  }
  const [effectiveDate, setEffectiveDate] = useState("");
  const [expirationDate, setExpirationDate] = useState("");

  const handleExtendContract = (e) => {
    e.preventDefault();
    const updateContract = { effectiveDate, expirationDate };
    return axios
      .put(
        "http://localhost:8090/api/contracts/" + contract.id,
        updateContract,
        { headers: authHeader() }
      )
      .then(() => {
        setShow(false);
        window.location.reload();
      });
  };

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Extend Policy
      </Button>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Extend Policy</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Effective Date:</Form.Label>
              <Form.Control
                type="date"
                onChange={(e) => setEffectiveDate(e.target.value)}
                autoFocus
              />
              <Form.Label>Expiration Date:</Form.Label>
              <Form.Control
                type="date"
                onChange={(e) => setExpirationDate(e.target.value)}
                autoFocus
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={(e) => handleExtendContract(e)}>
            Save
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
export default EditContractDateModal;
