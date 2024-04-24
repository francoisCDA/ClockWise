import {createBrowserRouter} from "react-router-dom";
import App from "./App";
import Signin from "./components/auth/Signin";
import Home from "./components/clockwise/Home";
import Listreport from "./components/clockwise/ListReport";
import Details from "./components/clockwise/DetailsEmployee";
import ProtectedRoute from "./components/auth/protectedRoute";

const router = createBrowserRouter([
    {path : "/",
    element : <Signin />},
        {
            path: "/home",
            element:<Home />
        },
        {
            path: "/listreport",
            element: <Listreport />
        },
        {
            path: "/employee/:id",
            element: <Details />
        }

])








export default router;