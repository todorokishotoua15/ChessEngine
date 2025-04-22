import "./CompGame.scss"
import { AppContext } from "../../Context/AppContext"
import { useContext, useState, useEffect } from "react";
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

const ChessBoard = () => {
    const [game, setGame] = useState(new Chess());
    const [position, setPosition] = useState(game.fen());
    const [selectedSquare, setSelectedSquare] = useState(null);
    const [validMoves, setValidMoves] = useState([]);
    const [turn, setTurn] = useState(game.turn());
    const [boardWidth, setBoardWidth] = useState(getResponsiveBoardSize());

    useEffect(() => {
        console.log("Current turn updated:", turn);
    }, [turn]);

    useEffect(() => {
        const handleResize = () => setBoardWidth(getResponsiveBoardSize());
        window.addEventListener('resize', handleResize);
        return () => window.removeEventListener('resize', handleResize);
    }, []);



    const safeGameMutate = (modify) => {
        setGame((g) => {
            const updated = new Chess(g.fen()); // clone to avoid state bugs
            modify(updated);
            setPosition(updated.fen());
            setTurn(updated.turn());
            return updated;
        });
    };

    const onSquareClick = (square) => {
        const gameClone = new Chess(game.fen()); // ✅ safe clone

        // Already selected and clicking valid destination
        if (selectedSquare && validMoves.includes(square)) {
            safeGameMutate((g) => g.move({ from: selectedSquare, to: square, promotion: 'q' }));
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
                        {turn === 'w' ? "White's Turn" : "Black's Turn"}
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
        content = <ChessBoard />;
    }

    return <div className="engine" >
        <NavBar />
        {content}
        <Footer />
    </div>
}

export default CompGame;