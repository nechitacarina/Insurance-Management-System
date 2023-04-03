import axios from "axios";
import { useEffect, useState } from "react";
import authHeader from "../services/auth-header";
import { Link } from "react-router-dom";
import NewClaimModal from "./NewClaimModal";

function BoardClient() {
  const [totalNumberOfContracts, setTotalNumberOfContracts] = useState(null);
  const [totalNumberOfExpiredContracts, setTotalNumberOfExpiredContracts] =
    useState(null);
  const [totalNumberOfActiveContracts, setTotalNumberOfActiveContracts] =
    useState(null);
  const [totalNumberOfClaims, setTotalNumberOfClaims] = useState(null);
  const [totalNumberOfAcceptedClaims, setTotalNumberOfAcceptedClaims] =
    useState(null);
  const [totalNumberOfRejectedClaims, setTotalNumberOfRejectedClaims] =
    useState(null);

  const URL_API_CLAIMS = "http://localhost:8090/api/claims/";
  const URL_API_CONTRACTS = "http://localhost:8090/api/contracts/";

  useEffect(() => {
    getTotalNumberOfContracts();
    getTotalNumberOfExpiredContracts();
    getTotalNumberOfActiveContracts();
    getTotalNumberOfClaims();
    getTotalNumberOfAcceptedClaims();
    getTotalNumberOfRejectedClaims();
  }, []);

  const getTotalNumberOfContracts = () => {
    return axios
      .get(URL_API_CONTRACTS + "count-loggedin-client", {
        headers: authHeader(),
      })
      .then((response) => {
        setTotalNumberOfContracts(response.data);
      });
  };

  const getTotalNumberOfExpiredContracts = () => {
    return axios
      .get(URL_API_CONTRACTS + "count-expired-loggedin-client", {
        headers: authHeader(),
      })
      .then((response) => {
        setTotalNumberOfExpiredContracts(response.data);
      });
  };

  const getTotalNumberOfActiveContracts = () => {
    return axios
      .get(URL_API_CONTRACTS + "count-active-loggedin-client", {
        headers: authHeader(),
      })
      .then((response) => {
        setTotalNumberOfActiveContracts(response.data);
      });
  };

  const getTotalNumberOfClaims = () => {
    return axios
      .get(URL_API_CLAIMS + "count-loggedin-client", { headers: authHeader() })
      .then((response) => {
        setTotalNumberOfClaims(response.data);
      });
  };

  const getTotalNumberOfAcceptedClaims = () => {
    return axios
      .get(URL_API_CLAIMS + "count-accepted-loggedin-client", {
        headers: authHeader(),
      })
      .then((response) => {
        setTotalNumberOfAcceptedClaims(response.data);
      });
  };

  const getTotalNumberOfRejectedClaims = () => {
    return axios
      .get(URL_API_CLAIMS + "count-rejected-loggedin-client", {
        headers: authHeader(),
      })
      .then((response) => {
        setTotalNumberOfRejectedClaims(response.data);
      });
  };

  return (
    <>
    <NewClaimModal></NewClaimModal>
      <div className="card-columns">
        <div className="card bg-primary">
          <div className="card-body text-center">
            <h3>
              <i className="bi bi-files"></i>
            </h3>
            <p className="card-text">
              Total Contracts: {totalNumberOfContracts}
            </p>
          </div>
        </div>
        <div className="card bg-warning">
          <div className="card-body text-center">
            <h3>
              <i className="bi bi-journal-x"></i>
            </h3>
            <p className="card-text">
              Expired Contracts: {totalNumberOfExpiredContracts}
            </p>
          </div>
        </div>
        <div className="card bg-success">
          <div className="card-body text-center">
            <h3>
              <i className="bi bi-journal-check"></i>
            </h3>
            <p className="card-text">
              Active Contracts: {totalNumberOfActiveContracts}
            </p>
          </div>
        </div>
        <div className="card bg-danger">
          <div className="card-body text-center">
            <h3>
              <i className="bi bi-patch-exclamation"></i>
            </h3>
            <p className="card-text">Total Claims: {totalNumberOfClaims}</p>
          </div>
        </div>
        <div className="card bg-info">
          <div className="card-body text-center">
            <h3>
              <i className="bi bi-file-earmark-check"></i>
            </h3>
            <p className="card-text">
              Accepted Claims: {totalNumberOfAcceptedClaims}
            </p>
          </div>
        </div>
        <div className="card bg-secondary">
          <div className="card-body text-center">
            <h3>
              <i className="bi bi-file-earmark-excel"></i>
            </h3>
            <p className="card-text">
              Rejected claims: {totalNumberOfRejectedClaims}
            </p>
          </div>
        </div>
      </div>
    </>
  );
}
export default BoardClient;
