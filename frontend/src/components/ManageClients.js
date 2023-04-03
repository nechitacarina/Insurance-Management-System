import axios from "axios";
import { useEffect, useState } from "react";
import { Dropdown } from "react-bootstrap";
import { Link } from "react-router-dom";
import authHeader from "../services/auth-header";
import "./manageclients.css";
import EditCredentialsModal from "./EditCredentialsModal";
import DeleteClientModal from "./DeleteClientModal";

function ManageClients() {
  const [clients, setClients] = useState([]);
  const URL_API_CLIENTS = "http://localhost:8090/api/clients/";
  const [filterVal, setFilterVal] = useState("");
  const [searchData, setSearchData] = useState([]);

  useEffect(() => {
    getAllClients();
  }, []);

  const getAllClients = () => {
    return axios
      .get(URL_API_CLIENTS, { headers: authHeader() })
      .then((response) => {
        setClients(response.data);
        setSearchData(response.data);
      });
  };

  const handleFilter = (e) => {
    if (e.target.value === "") {
      setClients(searchData);
    } else {
      const filterResult = searchData.filter((client) =>
        client.cnp.includes(e.target.value)
      );
      setClients(filterResult);
    }
    setFilterVal(e.target.value);
  };

  return (
    <>
      <Link to="/add-client" className="btn btn-primary mb-2" id="add-new">
        Add
      </Link>
      <input
        id="search-input"
        type="search"
        className="form-control rounded"
        placeholder="Search by CNP"
        aria-label="Search"
        aria-describedby="search-addon"
        onInput={(e) => handleFilter(e)}
      />
      <div className="table_container text-center">
        <table className=" table-bordered table-striped table-sm">
          <thead className="table-dark">
            <tr>
              <th>CNP</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>Date Of Birth</th>
              <th>Gender</th>
              <th>Income</th>
              <th>Phone Number</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {clients.map((client) => (
              <tr key={client.id}>
                <td>{client.cnp}</td>
                <td>{client.user.firstName}</td>
                <td>{client.user.lastName}</td>
                <td>{client.user.email}</td>
                <td>{client.dob}</td>
                <td>{client.gender}</td>
                <td>{client.income}</td>
                <td>{client.phoneNumber}</td>
                <td id="actions">
                  <Dropdown>
                    <Dropdown.Toggle variant="info" id="dropdown-basic">
                      Actions
                    </Dropdown.Toggle>
                    <Dropdown.Menu>
                      <Dropdown.Item
                        variant="secondary"
                        href={`edit/${client.id}`}
                      >
                        Edit
                      </Dropdown.Item>
                      <Dropdown.Item href={`contracts/${client.id}`}>
                        Contracts
                      </Dropdown.Item>
                      <Dropdown.Item href={`claims/${client.id}`}>
                        Claims
                      </Dropdown.Item>
                      <EditCredentialsModal u = {client.user} ></EditCredentialsModal>
                      <DeleteClientModal client={client}></DeleteClientModal>
                    </Dropdown.Menu>
                  </Dropdown>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}
export default ManageClients;
