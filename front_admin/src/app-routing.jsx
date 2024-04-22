import {createBrowserRouter} from "react-router-dom";
import App from "./App";
import Signin from "./components/auth/Signin";

const router = createBrowserRouter([
    {path : "/",
    element : <App />,
    children: [
        {
            path :"/signin",
            element : <Signin />
        }
    ]}
])








export default router;