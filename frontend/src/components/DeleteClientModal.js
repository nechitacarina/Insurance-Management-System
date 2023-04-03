import axios from "axios";
import { useState } from "react";
import authHeader from "../services/auth-header";
import { Button, Dropdown, Modal } from "react-bootstrap";

function DeleteClientModal({client}){
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
   const[id, setId] = useState(client.id);
  
    const handleDeleteClient = () => {
        return axios
          .delete("http://localhost:8090/api/clients/" + id, {headers: authHeader()})
          .then(() => {
              setShow(false);
              window.location.reload();
          });
  }
  
    return (
      <>
        <Dropdown.Item style={{paddingLeft: "25px", paddingTop:"0px"}} onClick={handleShow}>
          Delete
        </Dropdown.Item>
  
        <Modal
          show={show}
          onHide={handleClose}
          backdrop="static"
          keyboard={false}
        >
          <Modal.Header>
            <Modal.Title>Be Carefull!</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            If you want to delete a client this action will also delete their contracs and/or claims.
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            <Button variant="danger" onClick={handleDeleteClient} >Understood</Button>
          </Modal.Footer>
        </Modal>
      </>
    );
}
export default DeleteClientModal;