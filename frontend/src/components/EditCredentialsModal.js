import axios from "axios";
import { useState } from "react";
import { Button, Dropdown, Form, Modal } from "react-bootstrap";
import authHeader from "../services/auth-header";

function EditCredentialsModal(u) {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [password, setPassword] = useState("");

  const handleUpdatePassword = (e) => {
    return axios
      .put("http://localhost:8090/api/users/password/" + u.u.id, {password}, {headers: authHeader()})
      .then(() => {
        setShow(false);
        console.log(password);
      });
  };
  return (
    <>
      <Dropdown.Item
        style={{ paddingLeft: "25px", paddingTop: "2px" }}
        onClick={handleShow}
      >
        Change Password
      </Dropdown.Item>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Change Password</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Enter the password"
                onChange={(e) => setPassword(e.target.value)}
                autoFocus
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={(e) => handleUpdatePassword(e)}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
export default EditCredentialsModal;
