import axios from "axios";

const API_URL = "http://localhost:8090/api/auth/";

const login = (email, password) => {

    return axios.post(API_URL + "signin", {
        email,
        password
    }).then((response) => {
        if(response.data.token){
            localStorage.setItem("user", JSON.stringify(response.data)); //JSON.stringify() function is used to convert a JavaScript object into a string
        }
        return response.data;
    });
};

const logout = () =>{
    localStorage.removeItem("user");
}

const getCurrentUser = () =>{
    return JSON.parse(localStorage.getItem("user")); //parse() function is used to convert a string into a JavaScript object
}

const AuthService = {
    login,
    logout,
    getCurrentUser
};
export default AuthService;