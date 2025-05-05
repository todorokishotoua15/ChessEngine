package engine.engine.Interaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import engine.engine.Board.ArrayBoard.ArrayBoard;
import engine.engine.Piece.ArrayPieces.Piece;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

@Service
@Slf4j
public class Interaction {

    @Autowired
    private WebSocketStompClient stompClient;

    private StompSession stompSession;

    private HashMap<String, ArrayBoard> boardMap;

    @PostConstruct
    public void connectToWebSocket() throws Exception {
        String SERVER_URI = "ws://chess-broker:8853/ws";
        if (stompSession != null) {
            return;
        }
        log.info("Multiple connection");
        stompClient.connect(SERVER_URI, new StompSessionHandlerAdapter() {

            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                stompSession = session;
                log.info("Connected to WebSocket server");

                // Send a message to the server
                subscribeToEngine();
                boardMap = new HashMap<String, ArrayBoard>();
            }

            @Override
            public void handleTransportError(StompSession session, Throwable exception) {
                log.error("Error during WebSocket connection: ", exception);
            }
        }).get();
    }

    private void subscribeToEngine() {
        // Subscribe to /topic/engine to receive messages sent by the server to this
        // destination
        log.info("Subscribe called");
        stompSession.subscribe("/topic/engine", new MyStompFrameHandler());
    }

    private class MyStompFrameHandler implements
            org.springframework.messaging.simp.stomp.StompFrameHandler {

        @Override
        public Type getPayloadType(StompHeaders headers) {
            // Specify the type of message you expect. In this case, it's EngineModel.
            return EngineModel.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            // Handle the received message (EngineModel object)

            EngineModel data = (EngineModel) payload;
            int x = data.getX(), y = data.getY(), nex = data.getNex(), ney = data.getNey();

            log.info("Received move : x = {} y = {} nex = {} ney = {}, sessionId : {}", x, y, nex, ney, data.getFrom());

            String id = data.getFrom();
            if (!boardMap.containsKey(id)) {
                boardMap.put(id, new ArrayBoard());
            } else {
                if (data.getStarting() == 1) {
                    boardMap.remove(id);
                    boardMap.put(id, new ArrayBoard());
                }
            }
            ArrayBoard board = boardMap.get(id);
            boolean ret = board.move(x, y, nex, ney);
            log.info("Move passed ? {}", ret);
            ArrayList<Integer> nexMove = board.engineMove();

            log.info("nexMove : {}", nexMove);

            String append = board.getOccupied().get(nexMove.get(2) * 8 + nexMove.get(3)).getFenRep();
            String fromSquare = new String();
            String toSquare = new String();
            log.info("Got the next  move");
            // if (!append.equals("P") && !append.equals("p")) {
            // fromSquare += append;
            // toSquare += append;
            // }

            log.info("Got the next to  next move");
            char c1 = 'a';
            c1 += nexMove.get(1);
            char c2 = 'a';
            c2 += nexMove.get(3);

            fromSquare += c1;
            toSquare += c2;

            fromSquare += Integer.toString(nexMove.get(0) + 1);
            toSquare += Integer.toString(nexMove.get(2) + 1);
            log.info("Before creating dataToSend {} {} {} {}", fromSquare, toSquare, c1, c2, x, nex);
            EngineModel dataToSend = new EngineModel(nexMove.get(0), nexMove.get(1),
                    nexMove.get(2), nexMove.get(3), 1,
                    "engine", data.getFrom(), fromSquare, toSquare);

            log.info("Sending move from engine to /app/moves/engineMove: {}", dataToSend);
            stompSession.send("/app/moves/engineMove", dataToSend);
        }
    }

    @PreDestroy
    public void disconnectWebSocket() {
        if (stompSession != null && stompSession.isConnected()) {
            log.info("Disconnecting WebSocket session before shutdown...");
            stompSession.disconnect();
        }
    }
}
