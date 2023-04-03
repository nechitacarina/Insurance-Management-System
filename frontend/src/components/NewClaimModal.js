import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import { Button, Form, FormSelect, Modal } from "react-bootstrap";
import authHeader from "../services/auth-header";

function NewClaimModal() {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [categories, setCategories] = useState([]);
  const [category, setCategory] = useState("");
  const [policy, setPolicy] = useState("");
  const [policies, setPolicies] = useState([]);
  const [coverages, setCoverages] = useState([]);
  const [coverage, setCoverage] = useState("");
  const [amount, setAmount] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8090/api/categories/loggedInClient", {
        headers: authHeader(),
      })
      .then((response) => {
        setCategories(response.data);
      });
  }, []);

  const handleCategoryChange = (e) => {
    setCategory(e.target.value);
    axios
      .get(
        `http://localhost:8090/api/policies/loggedInClient-policyByCategory?id=${e.target.value}`,
        { headers: authHeader() }
      )
      .then((response) => {
        setPolicies(response.data);
      });
  };
  const handlePolicyChange = (e) => {
    setPolicy(e.target.value);
    axios
      .get(
        `http://localhost:8090/api/coverages/loggedInClient-coverageByPolicy?id=${e.target.value}`,
        { headers: authHeader() }
      )
      .then((response) => {
        setCoverages(response.data);
      });
  };

  const handleSaveNewClaim = (e) => {
    e.preventDefault();
    const claim = {category, policy, coverage, amount}
        axios
        .post("http://localhost:8090/api/claims/", claim, {headers: authHeader()})
        .then(() => {
              window.location.reload();
        });
  }
  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        New Claim
      </Button>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>New Claim</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Category:</Form.Label>
              <FormSelect
                name="category"
                className="form-control"
                value={category}
                onChange={handleCategoryChange}
              >
                <option defaultValue="selected">---SELECT CATEGORY---</option>
                {categories.map((categ) => (
                  <option key={categ.id} value={categ.id}>
                    {categ.title}
                  </option>
                ))}
              </FormSelect>
              <Form.Label>Policy:</Form.Label>
              <FormSelect
                name="policy"
                className="form-control"
                value={policy}
                onChange={handlePolicyChange}
              >
                <option defaultValue="selected">---SELECT POLICY---</option>
                {policies.map((pol) => (
                  <option key={pol.id} value={pol.id}>
                    {pol.title}
                  </option>
                ))}
              </FormSelect>
              <Form.Label>Coverage:</Form.Label>
              <FormSelect
                name="coverage"
                className="form-control"
                value={coverage}
                onChange={(e) => {
                  setCoverage(e.target.value);
                }}
              >
                <option defaultValue="selected">---SELECT COVERAGE---</option>
                {coverages.map((coverage) => (
                  <option key={coverage.id} value={coverage.id}>
                    {coverage.coveredRisk}
                  </option>
                ))}
              </FormSelect>
              <Form.Label>Amount:</Form.Label>
              <Form.Control
                type="number"
                name="amount"
                className="form-control"
                value={amount}
                placeholder="Enter the amount"
                onChange={(e) => setAmount(e.target.value)}
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={(e) => handleSaveNewClaim(e)}>Save Changes</Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
export default NewClaimModal;
