import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import authHeader from "../services/auth-header";
import NewContractModal from "./NewContractModal";
import EditContractDateModal from "./EditContractDateModal";

function ClientsContracts(){
    const[contracts, setContracts] = useState([]);
    const[currentContract, setCurrentContract] = useState("");
    const {id} = useParams();

    useEffect(() => {
        getContracts();
      }, []);

    const getContracts = () => {
       return axios
        .get("http://localhost:8090/api/contracts/client/" + id, {headers: authHeader()})
        .then((response) => {
            setContracts(response.data);
        });
    }
    for (let i = 0; i < contracts.length; i++) {
        if(contracts[i].id === id) setCurrentContract(contracts[i]);
    }

    return(
        <>
        <NewContractModal></NewContractModal>
        <div className='table_container text-center'>
                    <table className='table-bordered table-striped table-sm mt-5'>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Category</th>
                                <th>Policy</th>
                                <th>Effective Date</th>
                                <th>Expiration Date</th>
                                <th>Status</th>
                                <th>Claim Amount</th>
                                <th>Price</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                contracts.map(
                                    contract => 
                                    <tr key = {contract.id}>
                                        <td>{contract.id}</td>
                                        <td>{contract.policy.category.title}</td>
                                        <td>{contract.policy.title}</td>
                                        <td>{contract.effectiveDate}</td>
                                        <td>{contract.expirationDate}</td>
                                        <td>{contract.status}</td>
                                        <td>{contract.maxClaimAmount}</td>
                                        <td>{contract.price}</td>
                                        <td id ="actions">
                                            <EditContractDateModal contract={contract}></EditContractDateModal> 
                                        </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
                </>        
    );

}
export default ClientsContracts;