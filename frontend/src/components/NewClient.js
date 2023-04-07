import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "./newClient.css";
import authHeader from "../services/auth-header";
import axios from "axios";

function NewClient() {
  const [cnp, setCNP] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [dob, setDob] = useState("");
  const [gender, setGender] = useState("");
  const [income, setIncome] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [effectiveDate, seteffectiveDate] = useState("");
  const [expirationDate, setexpirationDate] = useState("");
  const [selectedCategory, setSelectedCategory] = useState("");
  const [categories, setCategories] = useState([]);
  const [policies, setPolicies] = useState([]);
  const [price, setPrice] = useState("");
  const [maxClaimAmount, setMaxClaimAmount] = useState("");
  const [selectedPolicy, setSelectedPolicy] = useState("");

  const generatePassword = () => {
    const randomPassword = { cnp }.cnp + "Parola0";
    setPassword(randomPassword);
  };
  const saveNewClient = (e) => {
    e.preventDefault();
    const user = { firstName, lastName, email, password };
    const client = { cnp, dob, gender, income, phoneNumber, user };
    const contract = {
      expirationDate,
      effectiveDate,
      maxClaimAmount,
      price,
      client,
    };
    const clientRequest = { client, user, contract, selectedPolicy };
    return axios
      .post("http://localhost:8090/api/clients", clientRequest, {
        headers: authHeader(),
      })
      .then(() => {
        window.location = "/manage-clients";
      });
  };

  useEffect(() => {
    getAllCategories();
  });

  const getAllCategories = () => {
    return axios
      .get("http://localhost:8090/api/categories", { headers: authHeader() })
      .then((response) => {
        setCategories(response.data);
      });
  };

  const handleCategoryChange = (e) => {
    setSelectedCategory(e.target.value);
    return axios
      .get("http://localhost:8090/api/policies/byCategory", {
        headers: authHeader(),
        params: { id: e.target.value },
      })
      .then((response) => {
        setPolicies(response.data);
      });
  };

  const handlePolicyChange = (e) => {
    setSelectedPolicy(e.target.value);
  };
  return (
    <form>
      <div className="row">
        <div className="column">
          <div className="form-headings">Personal Details</div>
          <label className="form-label">CNP:</label>
          <input
            type="text"
            placeholder="Enter client's CNP"
            name="cnp"
            className="form-control"
            value={cnp}
            onChange={(e) => setCNP(e.target.value)}
          ></input>
          <label className="form-label">First name:</label>
          <input
            type="text"
            placeholder="Enter client's first name"
            name="firstName"
            className="form-control"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
          ></input>
          <label className="form-label">Last name:</label>
          <input
            type="text"
            placeholder="Enter client's last name"
            name="lastName"
            className="form-control"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
          ></input>
          <label className="form-label">Date of birth:</label>
          <input
            type="date"
            placeholder="Enter client's date of birth"
            name="dob"
            className="form-control"
            value={dob}
            onChange={(e) => setDob(e.target.value)}
          ></input>
          <label className="form-label">Gender:</label>
          <div className="radios">
            <input
              id="gender-input-1"
              type="radio"
              name="gender"
              value="F"
              onChange={(e) => setGender(e.target.value)}
            />
            <label id="gender1">Female</label>
            <input
              id="gender-input-2"
              type="radio"
              name="gender"
              value="M"
              onChange={(e) => setGender(e.target.value)}
            />
            <label>Male</label>
          </div>
          <label className="form-label">Income:</label>
          <div className="input-group">
            <input
              type="number"
              placeholder="Enter client's income"
              name="income"
              className="form-control"
              value={income}
              onChange={(e) => setIncome(e.target.value)}
            ></input>
            <div className="input-group-append">
              <span className="input-group-text">$</span>
            </div>
          </div>
          <label className="form-label">Phone number:</label>
          <input
            type="number"
            placeholder="Enter client's phone number"
            name="phoneNumber"
            className="form-control"
            value={phoneNumber}
            onChange={(e) => setPhoneNumber(e.target.value)}
          ></input>
          <div className="form-headings">Account Details</div>
          <label className="form-label">Email:</label>
          <input
            type="email"
            placeholder="Enter client's email"
            name="email"
            className="form-control"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          ></input>
          <label className="form-label">Password:</label>
          <div className="input-group" id="password">
            <input
              readOnly={true}
              type="text"
              className="form-control"
              placeholder="Generate password"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <div className="input-group-append">
              <button
                disabled={!cnp}
                className="btn btn-dark"
                value="Generate Password"
                onClick={generatePassword}
                type="button"
              >
                <i className="bi bi-key"></i>
              </button>
            </div>
          </div>
        </div>
        <div className="column">
          <div className="form-headings">Contract</div>
          <label className="form-label">Insurance Category:</label>
          <select
            name="selectedCategory"
            className="form-control"
            value={selectedCategory}
            onChange={handleCategoryChange}
          >
            <option defaultValue="selected">---SELECT CATEGORY---</option>
            {categories.map((category) => (
              <option key={category.id} value={category.id}>
                {category.title}
              </option>
            ))}
          </select>
          <label className="form-label">Policy:</label>
          <select
            name="policy"
            className="form-control"
            value={selectedPolicy}
            onChange={handlePolicyChange}
          >
            <option defaultValue="selected">---SELECT POLICY---</option>
            {policies.map((policy) => (
              <option key={policy.id} value={policy.id}>
                {policy.title}
              </option>
            ))}
          </select>
          <label className="form-label">Effective date:</label>
          <input
            type="date"
            placeholder="Enter contract's effective date"
            name="effectiveDate"
            className="form-control"
            value={effectiveDate}
            onChange={(e) => seteffectiveDate(e.target.value)}
          ></input>
          <label className="form-label">Expiration date:</label>
          <input
            type="date"
            placeholder="Enter contract's expiration date"
            name="dateEnd"
            className="form-control"
            value={expirationDate}
            onChange={(e) => setexpirationDate(e.target.value)}
          ></input>
          <label className="form-label">Price:</label>
          <div className="input-group">
            <input
              type="number"
              name="price"
              className="form-control"
              value={price}
              onChange={(e) => setPrice(e.target.value)}
            ></input>
            <div className="input-group-append">
              <span className="input-group-text">$</span>
            </div>
          </div>
          <label className="form-label">Maximum claim amount:</label>
          <div className="input-group">
            <input
              type="number"
              name="maxClaimAmount"
              className="form-control"
              value={maxClaimAmount}
              onChange={(e) => setMaxClaimAmount(e.target.value)}
            ></input>
            <div className="input-group-append">
              <span className="input-group-text">$</span>
            </div>
          </div>
          <div id="buttons">
            <button
              id="save"
              className="btn btn-success"
              onClick={(e) => saveNewClient(e)}
            >
              Save
            </button>
            <Link to="/manage-clients" id="cancel" className="btn btn-danger">
              Cancel
            </Link>
          </div>
        </div>
      </div>
    </form>
  );
}
export default NewClient;
