import { Container, Row, Col } from 'react-bootstrap';
import Stack from '@mui/material/Stack';
import './LoginComp.scss'
import { useNavigate } from 'react-router';
import { Apps, LinkedIn } from "@mui/icons-material"
import axios from "axios";
import { ZapIcon, PlayIcon } from "@primer/octicons-react"
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useState } from 'react';
import { toast } from 'react-toastify'
import { useContext } from "react";
import { AppContext } from '../../Context/AppContext';
import { Spinner } from "react-bootstrap";

const BasicForm = ({ setWaiting }) => {

    const navigate = useNavigate()

    const { appState, setUser } = useContext(AppContext);

    console.log(appState, appState.token);

    if (appState.token !== null) {
        console.log(appState);
        navigate("/");
    }

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleEmailChange = (e) => setEmail(e.target.value);
    const handlePasswordChange = (e) => setPassword(e.target.value);



    const handleSubmit = (e) => {
        e.preventDefault();
        // You can access the entered values here
        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: 'http://localhost:9003/authservice/authenticate',
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                "email": email,
                "password": password
            }
        };

        setWaiting(true)

        axios.request(config)
            .then((response) => {
                let token = response.data.token;
                if (token === null) {
                    throw new Error("Incorrect credentials");
                }
                let user = response.data.user;
                console.log(user, token);

                setUser(user, token)
                navigate("/")
            })
            .catch((error) => {
                toast.error(error.message, {
                    position: "bottom-right",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "light",
                });
            }).finally(() => {
                setWaiting(false)

            });

    };

    return (
        <div className='basic-form-wrapper'>
            <Form className="p-4  text-white rounded" style={{
                // maxWidth: '400px',
                width: '100%',
                backgroundColor: 'rgba(0, 0, 0, 0.7)', // Slightly transparent black
                border: '2px solid #888',                // Subtle border
                boxShadow: '0 0 10px rgba(255, 255, 255, 0.1)', // soft white glow
                margin: '0 auto',   // Ensure the form is centered horizontally
                padding: '30px'  // Add some padding to make it more spacious
            }} onSubmit={handleSubmit}>
                <Form.Group className="mb-0" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email" onChange={handleEmailChange} value={email} />

                </Form.Group>
                <Form.Text style={{ color: "#000" }}>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</Form.Text>
                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" onChange={handlePasswordChange} value={password} />
                </Form.Group>

                <Button variant="dark" type="submit"
                    style={{
                        backgroundColor: '#333', // dark background color
                        borderColor: '#444', // darker border to match background
                        color: '#fff' // white text color for contrast
                    }}>
                    Submit
                </Button>
            </Form>
        </div>
    );
}


const LoginComp = () => {
    const [waiting, setWaiting] = useState(false);
    let content;
    const changeWait = (value) => { setWaiting(value) };
    if (waiting) {
        content = <Spinner variant="warning" />
    }
    else {
        content = <BasicForm setWaiting={changeWait} />
    }
    return (
        <div className='LoginComp '>
            {content}
        </div >
    );
};

export default LoginComp;