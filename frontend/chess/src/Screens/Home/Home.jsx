import "./Home.scss"
import { AppContext } from "../../Context/AppContext"
import { useContext } from "react";
import Hero from "../../Components/Hero/Hero";
import NavBar from "../../Components/Navbar/Navbar"
import Footer from "../../Components/Footer/Footer";

const Home = () => {
    const { appState } = useContext(AppContext);

    return <div className="Home">
        {/* <div className="titletext">Chess</div> */}
        <NavBar />
        <Hero />
        <Footer />
    </div>
}

export default Home;