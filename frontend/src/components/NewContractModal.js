import axios from "axios";
import { useEffect, useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import { useParams } from "react-router-dom";
import authHeader from "../services/auth-header";

function NewContractModal() {
  const [effectiveDate, setEffectiveDate] = useState("");
  const [expirationDate, setExpirationDate] = useState("");
  const [policies, setPolicies] = useState([]);
  const [price, setPrice] = useState("");
  const [maxClaimAmount, setMaxClaimAmount] = useState("");
  const [idPolicy, setIdPolicy] = useState("");
  const { id } = useParams();

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  useEffect(() => {
    getPolicies();
  }, []);

  const getPolicies = () => {
    return axios
      .get("http://localhost:8090/api/policies", { headers: authHeader() })
      .then((response) => {
        setPolicies(response.data);
      });
  }

  const handleSaveContract = (e) => {
    e.preventDefault();  
    const idClient = id;   
    const contract = {expirationDate, effectiveDate, maxClaimAmount,price, idClient, idPolicy}
    return axios
    .post("http://localhost:8090/api/contracts", contract, {headers: authHeader()})
    .then(() => {
        setShow(false);
        window.location = `/contracts/${id}`;
    });
}
  return (
    <>
      <Button style={{marginTop: "50px", marginLeft: "-90px"}} variant="primary" onClick={handleShow}>
        New Contract
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>New contract</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label className="form-label">Policy:</Form.Label>
              <select
                name="policy"
                className="form-control"
                value={idPolicy}
                onChange={(e) => {
                  setIdPolicy(e.target.value);
                }}
              >
                <option defaultValue="selected">---SELECT POLICY---</option>
                {policies.map((policy) => (
                  <option key={policy.id} value={policy.id}>
                    {policy.title}
                  </option>
                ))}
              </select>
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
              <Form.Label className="form-label">Price:</Form.Label>
              <Form.Control
                type="number"
                name="price"
                className="form-control"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
              ></Form.Control>
              <Form.Label className="form-label">
                Maximum claim amount:
              </Form.Label>
              <Form.Control
                type="number"
                name="maxClaimAmount"
                className="form-control"
                value={maxClaimAmount}
                onChange={(e) => setMaxClaimAmount(e.target.value)}
              ></Form.Control>
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={(e) => handleSaveContract(e)}>Save</Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
export default NewContractModal;
