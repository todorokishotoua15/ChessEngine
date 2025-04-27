import "./Home.scss"
import { AppContext } from "../../Context/AppContext"
import { useContext } from "react";
import Hero from "../../Components/Hero/Hero";
import NavBar from "../../Components/Navbar/Navbar"
import Footer from "../../Components/Footer/Footer";
import { useNavigate } from "react-router";

const Home = () => {
    const { appState } = useContext(AppContext);

    const navigate = useNavigate();
    console.log(appState);
    if (appState.token === null) {
        navigate("/login");
    }

    return <div className="Home">
        {/* <div className="titletext">Chess</div> */}
        <NavBar />
        <Hero />
        <Footer />
    </div>
}

export default Home;