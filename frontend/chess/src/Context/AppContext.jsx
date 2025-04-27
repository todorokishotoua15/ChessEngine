import React, { createContext, useState } from 'react';
import { Chessboard } from 'react-chessboard';
import { Chess } from 'chess.js';
export const AppContext = createContext();

export const ContextProvider = ({ children }) => {
    const [appState, setAppState] = useState({
        user: null,
        token: null
    });

    const [color, setColor] = useState(null);
    const [game, setGame] = useState(new Chess());
    const [winner, setWinner] = useState(null);
    const [gameOver, setGameOver] = useState(false);
    const [turn, setTurn] = useState("White");


    const setUser = (user, token) => {
        setAppState({ ...appState, user: user, token: token })
    }

    const setOptions = (newColor) => {
        var min = 1;
        var max = 20000;
        var rand = min + (Math.random() * (max - min));
        if (newColor === "Random") {
            newColor = (rand % 2 === 0 ? "White" : "Black"
            )
        }
        setColor(newColor);
    }

    function safeGameMutate(modify) {
        setGame((g) => {
            const update = { ...g };
            modify(update);
            return update;
        })
    }

    const updateTurn = () => {
        setTurn(turn === "white" ? "black" : "white");
    }

    return (
        <AppContext.Provider value={{ appState, color, game, turn, setOptions, safeGameMutate, updateTurn, setUser }} >
            {children}
        </AppContext.Provider>
    )
}