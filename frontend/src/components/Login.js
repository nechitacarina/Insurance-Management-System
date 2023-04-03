import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

import { isEmail } from "validator";

import AuthService from "../services/auth.service";
import { useNavigate } from "react-router-dom";
import { useRef, useState } from "react";

const required = value => {
    if (!value) {
      return (
        <div className="alert alert-danger" role="alert">
          This field is required!
        </div>
      );
    }
};
  
const email = value => {
    if (!isEmail(value)) {
      return (
        <div className="alert alert-danger" role="alert">
          This is not a valid email.
        </div>
      );
    }
};

function Login(){
    let navigate = useNavigate();

    const form = useRef();
    const checkBtn = useRef();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    const handleLogin = (e) => {
        e.preventDefault();
    
        setMessage("");
        
        form.current.validateAll();
    
        if (checkBtn.current.context._errors.length === 0) {
          AuthService.login(email, password).then(
            () => {
              navigate("/dashboard");
              window.location.reload();
            },
            (error) => {
              const resMessage =
                (error.response && error.response.data && error.response.data.message) || error.message ||
                error.toString();
                setLoading(false);
                setMessage(resMessage);
            }
          );
        }else {
            setLoading(false);
        }
    };

    return (
        <div className="col-md-12">
        <div className="card card-container">
            <img
            src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
            alt="profile-img"
            className="profile-img-card"
            />

            <Form onSubmit={handleLogin} ref={form}>
            <div className="form-group">
                <label htmlFor="email">Email</label>
                <Input
                type="email"
                className="form-control"
                name="email"
                value={email}
                onChange={(event) => setEmail(event.target.value)}
                validations={[required]}
                />
            </div>

            <div className="form-group">
                <label htmlFor="password">Password</label>
                <Input
                type="password"
                className="form-control"
                name="password"
                value={password}
                onChange={(event) => setPassword(event.target.value)}
                validations={[required]}
                />
            </div>

            <div className="form-group">
                <button className="btn btn-primary btn-block" disabled={loading}>
                {loading && (
                    <span className="spinner-border spinner-border-sm"></span>
                )}
                <span>Login</span>
                </button>
            </div>

            {message && (
                <div className="form-group">
                <div className="alert alert-danger" role="alert">
                    {message}
                </div>
                </div>
            )}
            <CheckButton style={{ display: "none" }} ref={checkBtn} />
            </Form>
        </div>
        </div>
    );
};

export default Login;