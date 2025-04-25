import { Container, Row, Col } from 'react-bootstrap';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import './Hero.scss'
import { useNavigate } from 'react-router';
import { LinkedIn } from "@mui/icons-material"

import { ZapIcon, PlayIcon } from "@primer/octicons-react"


const Hero = () => {

    const navigate = useNavigate()

    return (
        <div className='hero'>
            <Container className="text-center">
                <h1 style={{ fontSize: '3.5rem', fontWeight: 'bold' }}>Chess</h1>
                <p style={{ fontSize: '1.2rem', marginTop: '1rem' }}>
                    Choose an option to begin
                </p>
                <Row className="mt-4">
                    <Col className="d-flex justify-content-center">
                        <Button variant="outlined" startIcon={<PlayIcon size={24} />} size='large'
                            onClick={() => {
                                navigate("/engine");
                            }}
                            sx={{
                                textTransform: 'none',
                                fontSize: '1.2rem',           // make text bigger
                                padding: '12px 24px',         // increase padding
                                borderColor: 'white',         // white outline
                                color: 'white',
                                width: {
                                    xs: '100%',     // full width on mobile
                                    sm: '350px',     // auto on small screens and up
                                    md: '350px',    // fixed width on desktops
                                },               // white text
                                transition: 'all 0.2s ease-in-out',
                                boxShadow: '0px 2px 4px rgba(0, 0, 0, 0.2)',
                                backgroundColor: 'transparent',
                                '&:hover': {
                                    backgroundColor: 'grey.800', // hover background color
                                    borderColor: 'white',        // keep outline white on hover
                                    transform: 'translateY(-3px)',
                                    boxShadow: '0px 6px 12px rgba(0, 0, 0, 0.3)',
                                    color: 'white',              // keep text white on hover
                                },
                                '&:active': {
                                    transform: 'translateY(0px)',
                                    boxShadow: '0px 2px 4px rgba(0, 0, 0, 0.2)',
                                },
                            }}
                        > vs COMPUTER</Button>


                    </Col>
                </Row>
                <Row className="mt-4">
                    <Col className="d-flex justify-content-center">
                        <Button variant="outlined" startIcon={<PlayIcon size={24} />} size='large'
                            sx={{
                                textTransform: 'none',
                                fontSize: '1.2rem',           // make text bigger
                                padding: '12px 24px',         // increase padding
                                borderColor: 'white',         // white outline
                                color: 'white',
                                width: {
                                    xs: '100%',     // full width on mobile
                                    sm: '350px',     // auto on small screens and up
                                    md: '350px',    // fixed width on desktops
                                },               // white text
                                transition: 'all 0.2s ease-in-out',
                                boxShadow: '0px 2px 4px rgba(0, 0, 0, 0.2)',
                                backgroundColor: 'transparent',
                                '&:hover': {
                                    backgroundColor: 'grey.800', // hover background color
                                    borderColor: 'white',        // keep outline white on hover
                                    transform: 'translateY(-3px)',
                                    boxShadow: '0px 6px 12px rgba(0, 0, 0, 0.3)',
                                    color: 'white',              // keep text white on hover
                                },
                                '&:active': {
                                    transform: 'translateY(0px)',
                                    boxShadow: '0px 2px 4px rgba(0, 0, 0, 0.2)',
                                },
                            }}
                        > vs PLAYER</Button>


                    </Col>

                </Row>
            </Container>
        </div >
    );
};

export default Hero;