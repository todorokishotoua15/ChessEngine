import "./Register.scss"
import { AppContext } from "../../Context/AppContext"
import { useContext } from "react";
import Hero from "../../Components/Hero/Hero";
import NavBar from "../../Components/Navbar/Navbar"
import Footer from "../../Components/Footer/Footer";
import RegisterComp from "../../Components/Register/RegisterComp";

const Register = () => {
    const { appState } = useContext(AppContext);

    return <div className="Register">
        {/* <div className="titletext">Chess</div> */}
        <NavBar />
        <div className="page-content">
            <RegisterComp />
        </div>
        <Footer />
    </div>
}

export default Register;