import api from "./api";

const login = (mail, password) => {
  return api.post("/auth/admin", { mail, password }).then((response) => {
    if (response.data.token) {
      localStorage.setItem("user", JSON.stringify({ data: response.data }));
    }
    return response.data;
  });
};

export default { login };