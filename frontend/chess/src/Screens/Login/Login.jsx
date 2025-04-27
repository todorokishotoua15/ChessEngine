import "./Login.scss"
import { AppContext } from "../../Context/AppContext"
import { useContext } from "react";
import Hero from "../../Components/Hero/Hero";
import NavBar from "../../Components/Navbar/Navbar"
import Footer from "../../Components/Footer/Footer";
import LoginComp from "../../Components/LoginComp/LoginComp";

const Login = () => {
    const { appState } = useContext(AppContext);

    return <div className="Login">
        {/* <div className="titletext">Chess</div> */}
        <NavBar />
        <div className="page-content">
            <LoginComp />
        </div>
        <Footer />
    </div>
}

export default Login;