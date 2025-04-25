import "./CompGame.scss"
import { AppContext } from "../../Context/AppContext"
import { useContext, useState, useEffect, useRef } from "react";
import Hero from "../../Components/Hero/Hero";
import NavBar from "../../Components/Navbar/Navbar"
import Footer from "../../Components/Footer/Footer";
import { Container, Row, Col } from 'react-bootstrap';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router';
import { LinkedIn } from "@mui/icons-material"
import { ZapIcon, PlayIcon } from "@primer/octicons-react"
import { Chessboard } from 'react-chessboard';
import { Chess } from 'chess.js';
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";
import LinearProgress from '@mui/material/LinearProgress';
import Box from '@mui/material/Box';


const ColorSelector = ({ setOptionFunction }) => {

    return (
        <div className='cont mt-5'>
            <Container className="text-center">
                <h1 style={{ fontSize: '3.5rem', fontWeight: 'bold' }}>Chess</h1>
                <p style={{ fontSize: '1.2rem', marginTop: '1rem' }}>
                    Choose an option to begin
                </p>

                <Row className="mt-4">
                    <Col className="d-flex justify-content-center">
                        <Button variant="outlined" startIcon={<PlayIcon size={24} />} size='large'
                            onClick={() => setOptionFunction("White")}
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
                        > White </Button>
                    </Col>
                </Row>
                <Row className="mt-4">
                    <Col className="d-flex justify-content-center">
                        <Button variant="outlined" startIcon={<PlayIcon size={24} />} size='large'
                            onClick={() => setOptionFunction("Black")}
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
                        > Black </Button>
                    </Col>
                </Row>
                <Row className="mt-4">
                    <Col className="d-flex justify-content-center">
                        <Button variant="outlined" startIcon={<PlayIcon size={24} />} size='large'
                            onClick={() => setOptionFunction("Random")}
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
                        > Random </Button>
                    </Col>
                </Row>

            </Container >
        </div >
    );
}

const getResponsiveBoardSize = () => {
    const screenWidth = window.innerWidth;
    if (screenWidth < 576) return screenWidth - 32;       // Small phones
    if (screenWidth < 768) return 400;                    // Tablets
    if (screenWidth < 992) return 450;                    // Small laptops
    return 480;                                           // Desktops
};
let socketOpenedRef = null;
const ChessBoard = () => {
    const [game, setGame] = useState(new Chess());
    const [position, setPosition] = useState(game.fen());
    const [selectedSquare, setSelectedSquare] = useState(null);
    const [validMoves, setValidMoves] = useState([]);
    const [turn, setTurn] = useState(game.turn());
    const [boardWidth, setBoardWidth] = useState(getResponsiveBoardSize());

    const [sessionId, setSessionId] = useState(null);
    const sessionIdRef = useRef(null);

    const [message, setMessage] = useState('');
    let stompClientRef = useRef(null);

    const [socketOpened, setSocketOpened] = useState(0);


    const safeGameMutate = (modify) => {
        setGame((g) => {
            const updated = new Chess(g.fen()); // clone to avoid state bugs
            modify(updated);
            setPosition(updated.fen());
            setTurn(updated.turn());
            return updated;
        });
    };

    // useEffect(() =>)

    useEffect(() => {
        if (sessionIdRef.current === null) {
            console.log("Here")
            const id = crypto.randomUUID(); // or use any unique string generator
            sessionIdRef.current = id;

        }
    }, [])


    useEffect(() => {
        console.log("Current turn updated:", turn);
    }, [turn]);

    useEffect(() => {
        const handleResize = () => setBoardWidth(getResponsiveBoardSize());
        window.addEventListener('resize', handleResize);
        return () => window.removeEventListener('resize', handleResize);
    }, []);

    useEffect(() => {
        console.log('Attempting to initiate socket connection');
        // const socket = new SockJS('http://localhost:8853/ws');
        const socket = new SockJS('https://mpntx0-ip-119-161-98-68.tunnelmole.net/ws');
        const stompClient = new Client({
            webSocketFactory: () => socket,
            reconnectDelay: 5000,
            debug: (str) => {
                console.log(str);
            },
            onConnect: () => {
                console.log("Connected to WebSocket");
                setSocketOpened(1);
                stompClient.subscribe('/topic/user', (response) => {
                    const data = JSON.parse(response.body);
                    let x = data.x;
                    let y = data.y;
                    let nex = data.nex;
                    let ney = data.ney;
                    let fromSquare = data.fromSquare
                    let toSquare = data.toSquare
                    let intendedId = data.to;
                    console.log("Session id = " + sessionIdRef.current + "Intended id = " + intendedId);
                    if (intendedId === sessionIdRef.current) {


                        try {
                            safeGameMutate((g) => g.move({ from: fromSquare, to: toSquare, promotion: 'q' }));
                            setSelectedSquare(null);
                            setValidMoves([]);
                        } catch (e) {
                            console.log(e);
                        }
                    }


                })
            },
            onStompError: (frame) => {
                console.error('Broker reported error: ' + frame.headers['message']);
                console.error('Additional details: ' + frame.body);
            }
        })

        stompClient.activate();
        stompClientRef.current = stompClient;
        return () => {
            stompClient.deactivate();
        };
    }, [])

    const sendMessage = (from, to) => {
        const stompClient = stompClientRef.current;
        let x;
        let y;
        let nex;
        let ney;
        let ref1 = "a";
        let ref2 = "0";
        if (from.length === 3) {
            x = from.charCodeAt(2) - ref2.charCodeAt(0) - 1;
            y = from.charCodeAt(1) - ref1.charCodeAt(0);
        }
        else {
            x = from.charCodeAt(1) - ref2.charCodeAt(0) - 1;
            y = from.charCodeAt(0) - ref1.charCodeAt(0);
        }
        if (to.length === 3) {
            nex = to.charCodeAt(2) - ref2.charCodeAt(0) - 1;
            ney = to.charCodeAt(1) - ref1.charCodeAt(0);
        }
        else {
            nex = to.charCodeAt(1) - ref2.charCodeAt(0) - 1;
            ney = to.charCodeAt(0) - ref1.charCodeAt(0);
        }
        // x--, nex--;
        const payload = JSON.stringify({
            x: x,
            y: y,
            nex: nex,
            ney: ney,
            from: sessionIdRef.current,
            to: "engine",
            fromSquare: from,
            toSquare: to
        })
        if (stompClient && stompClient.connected) {
            console.log('Sending the move : ', from, to, payload);
            stompClient.publish({
                destination: '/app/moves/userMove',
                body: payload,

            })
        } else {
            console.error('Stomp client is not connected');
        }

    }




    const onSquareClick = (square) => {
        const gameClone = new Chess(game.fen()); // ✅ safe clone

        // Already selected and clicking valid destination
        if (selectedSquare && validMoves.includes(square)) {
            safeGameMutate((g) => g.move({ from: selectedSquare, to: square, promotion: 'q' }));
            sendMessage(selectedSquare, square);
            setSelectedSquare(null);
            setValidMoves([]);
            return;
        }

        // Try selecting a new piece
        const piece = gameClone.get(square); // ✅ safe read

        if (piece && piece.color === gameClone.turn()) {
            const moves = gameClone.moves({ square, verbose: true }); // ✅ safe read
            const destinations = moves.map((m) => m.to);

            setSelectedSquare(square);
            setValidMoves(destinations);
        } else {
            // invalid selection or clicked on blank/other side’s piece
            setSelectedSquare(null);
            setValidMoves([]);
        }
    };

    const onPieceDrop = (sourceSquare, targetSquare) => {
        const gameCopy = new Chess(game.fen()); // clone game safely
        // let moveList = gameCopy.moves({ "square": sourceSquare });
        // console.log(sourceSquare, targetSquare, moveList);
        // if (!moveList.includes(targetSquare)) return false;
        let move;
        try {
            move = gameCopy.move({
                from: sourceSquare,
                to: targetSquare,
                promotion: 'q', // always promote to queen for now
            });
            sendMessage(sourceSquare, targetSquare);
        } catch (e) {
            return false;
        }
        console.log(move);
        if (move === null) return false; // ❌ invalid move

        setGame(gameCopy);                // ✅ update to the new valid game
        setPosition(gameCopy.fen());     // 🧠 also update position
        setTurn(gameCopy.turn());        // ♟ show who's turn
        setSelectedSquare(null);
        setValidMoves([]);

        return true; // ✅ valid move
    };

    const getSquareStyles = () => {
        const styles = {};

        if (selectedSquare) {
            styles[selectedSquare] = {
                background: 'radial-gradient(circle, rgba(0, 150, 136, 0.35) 45%, transparent 60%)', // teal
            };
        }

        validMoves.forEach((sq) => {
            styles[sq] = {
                background: 'radial-gradient(circle, rgba(255, 193, 7, 0.35) 35%, transparent 60%)', // amber
            };
        });

        return styles;
    };

    return (
        <Container fluid
            className="d-flex flex-column justify-content-center align-items-center"
        >
            <Row className="d-flex flex-column align-items-center justify-content-center">
                <Col className="d-flex flex-column align-items-center justify-content-center">
                    <h3 style={{ textAlign: 'center', color: '#fff' }}>
                        {turn === 'w' ? "White's Turn" : "Black's Turn"} {socketOpened === 0 ? "(Connecting...)" : "(Connected)"}
                    </h3>
                    <Chessboard
                        position={position}
                        onSquareClick={onSquareClick}
                        boardWidth={boardWidth}
                        onPieceDrop={onPieceDrop}
                        customSquareStyles={getSquareStyles()}
                        customDarkSquareStyle={{
                            backgroundColor: '#3c3c3c', // soft charcoal
                        }}
                        customLightSquareStyle={{
                            backgroundColor: '#e0e0e0', // gentle off-white
                        }}
                    />
                </Col>
            </Row>
        </Container >
    );
}



const CompGame = () => {
    const { appState, color, setOptions, } = useContext(AppContext);
    // const []

    let content;

    if (color === null) {
        content = <ColorSelector setOptionFunction={setOptions} />
    }
    else {
        // stompClientRef.current === null
        content = <ChessBoard />

    }

    return <div className="engine" >
        <NavBar />
        {content}
        <Footer />
    </div>
}

export default CompGame;