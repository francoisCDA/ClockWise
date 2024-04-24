import api from "./api";

const login = (email, password) => {
  console.log("authservice" + email + " " + password)
  return api.post("/user/login", { email, password }).then((response) => {
    console.log("toto, rentrer api")
    console.log(response.data)
    if (response.data.data.token) {
      console.log(response.data.data.token)
      localStorage.setItem("token", JSON.stringify( response.data.data.token ));
    }
    return response.data;
  });
};




const logout = () => {
  localStorage.removeItem('token');
};

export default { login, logout };