export function authHeader() {
    const user = JSON.parse(localStorage.getItem("token"));
    if (user && user.data.token) {
      return { Authorization: "Bearer " + user.data.token };
    } else {
      return {};
    }
  }