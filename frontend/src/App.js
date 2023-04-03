import { useEffect, useState } from 'react';
import { Link, Route, Routes } from 'react-router-dom';
import './App.css';
import BoardAgent from './components/BoardAgent';
import BoardClient from './components/BoardClient';
import Home from './components/Home';
import Login from './components/Login';
import AuthService from './services/auth.service';
import Dashboard from './components/Dashboard';
import ManageClients from './components/ManageClients';
import NewClient from './components/NewClient';
import EditClientPersonalInfo from './components/EditClientPersonalInfo';
import ClientsContracts from './components/Client\'sContracts';
import ClientsClaims from './components/Client\'sClaims';

function App() {

  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if(user){
      setCurrentUser(user);
    }
  }, [])
  const logout = () => {
    AuthService.logout();
  }
  return (
    <div>
       <nav className="navbar navbar-expand-md navbar-dark bg-dark">
       {currentUser ? (
          <div className="navbar-nav">
          <li className="nav-item">
            <Link to={"/dashboard"} className="navbar-brand">
              InsuranceMS
            </Link>
          </li>
        </div>
        ): (
          <div className="navbar-nav">
            <li className="nav-item">
            <Link to={"/"} className="navbar-brand">
              InsuranceMS
            </Link>
            </li>
          </div>
        )}
        <div className="navbar-nav mr-auto">
          {currentUser && currentUser.role === "[ROLE_AGENT]" && (
              <li className="nav-item">
                <Link to={"/manage-clients"} className="nav-link">
                  Manage Clients
                </Link>
              </li>
          )}
          </div>

        {currentUser ? (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <div className="navbar-text">
                {currentUser.username}
              </div>
            </li>
            <li className="nav-item ml-3">
              <a href="/login" className="btn btn-warning" onClick={logout}>
                Logout
              </a>
            </li>
          </div>
        ) : (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/login"} className="btn btn-warning">
                Login
              </Link>
            </li>
          </div>
        )}

      </nav>
      <div className="container mt-3">
        <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="/login" element={<Login/>} />
          <Route path="/dashboard" element={<Dashboard/>} />
          <Route path="/client" element={<BoardClient/>} />
          <Route path="/agent" element={<BoardAgent/>} />
          <Route path="/manage-clients" element={<ManageClients/>} />
          <Route path="/add-client" element={<NewClient/>} />
          <Route path='edit/:id' element={<EditClientPersonalInfo/>}></Route>
          <Route path='contracts/:id' element={<ClientsContracts/>}></Route>
          <Route path='claims/:id' element={<ClientsClaims/>}></Route>
        </Routes>
      </div>
      <footer className="AppFooter">
        <div>InsuranceMs ©2023 Created by Carina Nechita</div>
      </footer>
    </div>
  );
}

export default App;
