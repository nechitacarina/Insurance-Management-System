import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import authHeader from "../services/auth-header";
import "./EditClientPersonalInfo.css";

function EditClientPersonalInfo() {
  const [cnp, setCNP] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [dob, setDob] = useState("");
  const [gender, setGender] = useState("");
  const [income, setIncome] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const { id } = useParams();

  useEffect(() => {
    axios
      .get("http://localhost:8090/api/clients/" + id, { headers: authHeader() })
      .then((response) => {
        console.log(response.data);
        setCNP(response.data.cnp);
        setDob(response.data.dob);
        setGender(response.data.gender);
        setIncome(response.data.income);
        setPhoneNumber(response.data.phoneNumber);
        setFirstName(response.data.user.firstName);
        setLastName(response.data.user.lastName);
        setEmail(response.data.user.email);
      });
  }, []);

  const updateClientPersonalInfo = (e) => {
    e.preventDefault();
    const user = { firstName, lastName, email };
    const client = { cnp, dob, gender, income, phoneNumber, user };
    return axios
      .put("http://localhost:8090/api/clients/info/" + id, client, {
        headers: authHeader(),
      })
      .then(() => {
        window.location = "/manage-clients";
      });
  };

  return (
    <form>
      <div className="column-edit">
        <div className="form-headings">Personal Details</div>
        <label className="form-label">CNP:</label>
        <input
          type="text"
          placeholder="Enter client's CNP"
          name="cnp"
          className="form-control"
          defaultValue={cnp}
          onChange={(e) => setCNP(e.target.value)}
        ></input>
        <label className="form-label">First name:</label>
        <input
          type="text"
          placeholder="Enter client's first name"
          name="firstName"
          className="form-control"
          defaultValue={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        ></input>
        <label className="form-label">Last name:</label>
        <input
          type="text"
          placeholder="Enter client's last name"
          name="lastName"
          className="form-control"
          defaultValue={lastName}
          onChange={(e) => setLastName(e.target.value)}
        ></input>
        <label className="form-label">Date of birth:</label>
        <input
          type="date"
          placeholder="Enter client's date of birth"
          name="dob"
          className="form-control"
          defaultValue={dob}
          onChange={(e) => setDob(e.target.value)}
        ></input>
        <label className="form-label">Gender:</label>
        <div className="radios">
          <input
            type="radio"
            name="gender"
            defaultValue="F"
            onChange={(e) => setGender(e.target.value)}
            checked={gender === "F"}
          />
          <label>Female</label>
          <input
            type="radio"
            name="gender"
            defaultValue="M"
            onChange={(e) => setGender(e.target.value)}
            checked={gender === "M"}
          />
          <label>Male</label>
        </div>
      </div>
      <div className="column-edit" style={{ paddingTop: "69px" }}>
        <label className="form-label">Income:</label>
        <input
          type="number"
          placeholder="Enter client's income"
          name="income"
          className="form-control"
          defaultValue={income}
          onChange={(e) => setIncome(e.target.value)}
        ></input>
        <label className="form-label">Phone number:</label>
        <input
          type="number"
          placeholder="Enter client's phone number"
          name="phoneNumber"
          className="form-control"
          defaultValue={phoneNumber}
          onChange={(e) => setPhoneNumber(e.target.value)}
        ></input>

        <div className="form-headings">Account Details</div>
        <label className="form-label">Email:</label>
        <input
          type="email"
          placeholder="Enter client's email"
          name="email"
          className="form-control"
          defaultValue={email}
          onChange={(e) => setEmail(e.target.value)}
        ></input>
      </div>
      <div className="editButtons">
        <button
          id="save"
          className="btn btn-success"
          onClick={(e) => updateClientPersonalInfo(e)}
        >
          Save
        </button>
        <Link to="/manage-clients" id="cancel" className="btn btn-danger">
          Cancel
        </Link>
      </div>
    </form>
  );
}
export default EditClientPersonalInfo;
