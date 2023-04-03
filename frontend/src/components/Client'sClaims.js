import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import { useParams } from "react-router-dom";
import authHeader from "../services/auth-header";
import UpdateClaimStatusModal from "./UpdateClaimStatusModal";

function ClientsClaims() {
  const [claims, setClaims] = useState([]);
  const { id } = useParams();
  useEffect(() => {
    getClaims();
  }, []);

  const getClaims = () => {
    return axios
      .get("http://localhost:8090/api/claims/client/" + id, {
        headers: authHeader(),
      })
      .then((response) => {
        setClaims(response.data);
      });
  };

  return (
        <div className='table-container text-center'>
                <table className='table-bordered table-striped table-sm'>
                    <thead>
                        <tr>
                            <th>Claim Number</th>
                            <th>Client's CNP</th>
                            <th>Date Of Submission</th>
                            <th>Policy</th>
                            <th>Reason</th>
                            <th>Status</th>
                            <th>Amount</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            claims.map(
                                claim => 
                                <tr key = {claim.id}>
                                    <td>{claim.id}</td>
                                    <td>{claim.client.cnp}</td>
                                    <td>{claim.submissionDate}</td>
                                    <td>{claim.policy.title}</td>
                                    <td>{claim.coverage.coveredRisk}</td>
                                    <td>{claim.status}</td>
                                    <td>{claim.amount}</td>
                                    <td id ="actions">
                                        <UpdateClaimStatusModal claim={claim}></UpdateClaimStatusModal>
                                    </td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        
    );
}
export default ClientsClaims;
