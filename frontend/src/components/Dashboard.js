import { useEffect, useState } from "react";
import AuthService from "../services/auth.service"
import BoardAgent from "./BoardAgent";
import BoardClient from "./BoardClient";

function Dashboard(){
    const currentUser = AuthService.getCurrentUser();

    const [showAgentBoard, setShowAgentBoard] = useState(false);
    const [showClientBoard, setShowClientBoard] = useState(false);


    useEffect(() => {
        setShowAgentBoard(currentUser.role.includes("ROLE_AGENT"));
        setShowClientBoard(currentUser.role.includes("ROLE_CLIENT"));
      }, [])
        
    return(
    <div>
          {showAgentBoard && (
                <BoardAgent></BoardAgent>
          )}
          {showClientBoard && (
              <BoardClient></BoardClient>
          )}
        </div>
    )
}
export default Dashboard;