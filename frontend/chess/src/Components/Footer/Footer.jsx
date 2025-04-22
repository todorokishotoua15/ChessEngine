import './Footer.scss'

// import { AppContext } from '../Context/AppContext';
import { useContext } from 'react';
import { useNavigate } from 'react-router';

import BottomNavigation from '@mui/material/BottomNavigation';
import BottomNavigationAction from '@mui/material/BottomNavigationAction';
import FolderIcon from '@mui/icons-material/Folder';
import RestoreIcon from '@mui/icons-material/Restore';
import FavoriteIcon from '@mui/icons-material/Favorite';
import LocationOnIcon from '@mui/icons-material/LocationOn';

import * as React from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import { GitHub, Info, Instagram, LinkedIn } from '@mui/icons-material';



const Footer = () => {

    const [value, setValue] = React.useState('recents');

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    return (
        <Container className='mt-5 fluid' >
            <Row className='d-flex justify-content-center' style={{ marginTop: '80px' }}>
                {/* <Col className=''> */}
                <BottomNavigation sx={{
                    width: '75vw', // full screen width
                    position: 'fixed',
                    width: '100%',
                    zIndex: 1000,
                    left: 0,
                    bottom: 0,
                    // tp[]
                    backgroundColor: 'rgba(0, 0, 0, 0.6)',
                    borderTop: '1px solid white',
                    color: 'white',
                    // pt: 2,
                    height: '100px',
                    '& .MuiBottomNavigationAction-root': {
                        color: 'grey.400',
                        minWidth: 0,
                        flex: 1,
                        padding: 0,
                        height: '100%',
                        display: 'flex',
                        flexDirection: 'column',
                        justifyContent: 'center',
                        position: 'relative',
                        overflow: 'hidden',
                        '&::before': {
                            content: '""',
                            position: 'absolute',
                            top: 0,
                            left: 0,
                            width: '100%',
                            height: '100%',
                            backgroundColor: 'rgba(255, 255, 255, 0.08)', // Hover overlay
                            opacity: 0,
                            transition: 'opacity 0.3s ease',
                            pointerEvents: 'none',
                            zIndex: 1,
                        },
                        '&:hover::before': {
                            opacity: 1, // Only the hovered action gets the effect
                        },

                    },

                    '& .MuiBottomNavigationAction-label': {
                        fontSize: '0.75rem',
                        mt: 0.5,
                    },
                    '& .MuiSvgIcon-root': {
                        zIndex: 2,
                    },
                    '& .Mui-selected': {
                        color: 'white',
                    },
                    '& .MuiTouchRipple-root': {
                        width: '100%',
                        height: '100%',
                    },
                    '& .MuiBottomNavigationAction-root .MuiTouchRipple-root span': {
                        backgroundColor: 'rgba(255, 255, 255, 0.3)',
                    },
                }} value={value} onChange={handleChange} >
                    <BottomNavigationAction
                        label="LinkedIn"
                        value="linkedin"
                        icon={<LinkedIn />}
                    />
                    <BottomNavigationAction
                        label="Instagram"
                        value="instagram"
                        icon={<Instagram />}
                    />
                    <BottomNavigationAction
                        label="Github"
                        value="github"
                        icon={<GitHub />}
                    />
                    <BottomNavigationAction label="About Us" value="aboutus" icon={<Info />} />
                </BottomNavigation>
                {/* </Col> */}
            </Row>
        </Container >

    );
}

export default Footer;