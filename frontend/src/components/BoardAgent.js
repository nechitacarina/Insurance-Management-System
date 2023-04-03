import axios from "axios";
import { useEffect, useState } from "react";
import authHeader from "../services/auth-header";
import "./boardagent.css";

function BoardAgent(){

    const URL_API_CLIENTS = "http://localhost:8090/api/clients/";
    const URL_API_CLAIMS = "http://localhost:8090/api/claims/";
    const URL_API_CATEGORIES = "http://localhost:8090/api/categories/";
    const [totalNumberOfClients, setTotalNumberOfClients] = useState(null);
    const [totalNumberOfClientsWithVehicleInsurance, setTotalNumberOfClientsWithVehicleInsurance] = useState(null);
    const [totalNumberOfClientsWithPropertyInsurance, setTotalNumberOfClientsWithPropertyInsurance] = useState(null);
    const [totalNumberOfClientsWithLifeInsurance, setTotalNumberOfClientsWithLifeInsurance] = useState(null);
    const [totalNumberOfCategories, setTotalNumberOfCategories] = useState(null);
    const [totalNumberOfPendingClaims, setTotalNumberOfPendingClaims] = useState(null);
    useEffect(()=>{
        getTotalNumberOfClients();
        getTotalNumberOfClientsWithVehicleInsurance();
        getTotalNumberOfClientsWithPropertyInsurance();
        getTotalNumberOfClientsWithLifeInsurance();
        getTotalNumberOfCategories();
        getTotalNumberOfPendingClaims();
    },[])

    const getTotalNumberOfClients = () =>{
        return axios.get(URL_API_CLIENTS + "count", {headers: authHeader()}).then((response) => {
            setTotalNumberOfClients(response.data);
        });
    }

    const getTotalNumberOfClientsWithVehicleInsurance = () =>{
        return axios.get(URL_API_CLIENTS + "count-vehicle-insurance", {headers: authHeader()}).then((response) => {
            setTotalNumberOfClientsWithVehicleInsurance(response.data);
        });
    }

    const getTotalNumberOfClientsWithPropertyInsurance = () =>{
        return axios.get(URL_API_CLIENTS + "count-property-insurance", {headers: authHeader()}).then((response) => {
            setTotalNumberOfClientsWithPropertyInsurance(response.data);
        });
    }

    const getTotalNumberOfClientsWithLifeInsurance = () =>{
        return axios.get(URL_API_CLIENTS + "count-life-insurance", {headers: authHeader()}).then((response) => {
            setTotalNumberOfClientsWithLifeInsurance(response.data);
        });
    }

    const getTotalNumberOfCategories = () =>{
        return axios.get(URL_API_CATEGORIES + "count", {headers: authHeader()}).then((response) => {
            setTotalNumberOfCategories(response.data);
        });
    }

    const getTotalNumberOfPendingClaims = () =>{
        return axios.get(URL_API_CLAIMS + "count", {headers: authHeader()}).then((response) => {
            setTotalNumberOfPendingClaims(response.data);
        });
    }
    return (
        <div className="card-columns">
            <div className="card bg-primary">
                <div className="card-body text-center">
                    <h3><i className="bi bi-people-fill"></i></h3>
                    <p className="card-text">Total Clients: {totalNumberOfClients}</p>
                </div>
            </div>
            <div className="card bg-warning">
                <div className="card-body text-center">
                    <h3><i className="bi bi-truck-front-fill"></i></h3>
                    <p className="card-text">Vehicle insured clients: {totalNumberOfClientsWithVehicleInsurance}</p>
                </div>
            </div>
            <div className="card bg-success">
                <div className="card-body text-center">
                    <h3><i className="bi bi-buildings-fill"></i></h3>
                    <p className="card-text">Property insured clients: {totalNumberOfClientsWithPropertyInsurance}</p>
                </div>
            </div>
            <div className="card bg-danger">
                <div className="card-body text-center">
                    <h3><i className="bi bi-bandaid-fill"></i></h3>
                    <p className="card-text">Life insured clients: {totalNumberOfClientsWithLifeInsurance}</p>
                </div>
            </div>
            <div className="card bg-info">
                <div className="card-body text-center">
                    <h3><i className="bi bi-tags-fill"></i></h3>
                    <p className="card-text">Policy categories: {totalNumberOfCategories}</p>
                </div>
            </div>
            <div className="card bg-secondary">
                <div className="card-body text-center">
                    <h3><i className="bi bi-hourglass-split"></i></h3>
                    <p className="card-text">Pending claims: {totalNumberOfPendingClaims}</p>
                </div>
            </div>
      </div>
    );
};
export default BoardAgent;