import {createBrowserRouter} from "react-router-dom";
import App from "./App";
import Signin from "./components/auth/Signin";
import Home from "./components/clockwise/Home";
import Listreport from "./components/clockwise/ListReport";
import Details from "./components/clockwise/DetailsEmployee";

const router = createBrowserRouter([
    {path : "/",
    element : <App />,
    children: [
        {
            path :"/",
            element : <Signin />
        },
        {
            path: "/home",
            element: <Home />
        },
        {
            path: "/listreport",
            element: <Listreport />
        },
        {
            path: "/details/:id",
            element: <Details />
        }

    ]}
])








export default router;