import {createBrowserRouter} from "react-router-dom";
import App from "./App";
import Signin from "./components/auth/Signin";
import Home from "./components/clockwise/Home";

const router = createBrowserRouter([
    {path : "/",
    element : <App />,
    children: [
        {
            path :"/signin",
            element : <Signin />
        },
        {
            path: "/home",
            element: <Home />
        }
    ]}
])








export default router;