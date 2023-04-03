import axios from "axios";
import { useState } from "react";
import authHeader from "../services/auth-header";
import { Button, Modal } from "react-bootstrap";

function UpdateClaimStatusModal({ claim }) {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const handleAcceptClaim = (e) => {
    e.preventDefault();
    return axios
      .put(
        "http://localhost:8090/api/claims/accept/" + claim.id,
        {},
        {headers: authHeader()}
      )
      .then(() => {
        setShow(false);
        window.location.reload();
      });
  };
  const handleRejectClaim = (e) => {
    e.preventDefault();
    return axios
      .put(
        "http://localhost:8090/api/claims/reject/" + claim.id,
        {},
        {headers: authHeader()}
      )
      .then(() => {
        setShow(false);
        window.location.reload();
      });
  };
  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        ACCEPT/REJECT
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Update Claim Status</Modal.Title>
        </Modal.Header>
        <Modal.Body>What would you like to do?</Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={(e) => handleAcceptClaim(e)}>
            Accept
          </Button>
          <Button variant="danger" onClick={(e) => handleRejectClaim(e)}>
            Reject
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
export default UpdateClaimStatusModal;
