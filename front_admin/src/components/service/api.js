import axios from "axios";

const api = axios.create({
  baseURL: "http://10.125.51.52:8000/cwise/api/v2",
});

export default api;