package broker.broker.Controller;

import org.apache.catalina.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import broker.broker.Model.EngineModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EngineController {

    @Autowired
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/moves/userMove")
    public void sendUserMoveToEngine(@Payload EngineModel data, SimpMessageHeaderAccessor headerAccessor) {
        log.info("Data recieved : x = {}  y = {} nex = {} ney = {}", data.getX(), data.getY(), data.getNex(),
                data.getNey());
        headerAccessor.getSessionAttributes().put("from", data.getFrom());
        messagingTemplate.convertAndSend("/topic/engine", data);
    }

    @MessageMapping("/moves/engineMove")
    public void sendEngineMoveToUser(@Payload EngineModel data, SimpMessageHeaderAccessor headerAccessor) {
        // String username = data.getTo();
        // messagingTemplate.convertAndSendToUser(username, "/queue/engine", data);
        log.info("Data recieved from engine : x = {}  y = {} nex = {} ney = {}", data.getX(), data.getY(), data.getNex(),
                data.getNey());
        messagingTemplate.convertAndSend("/topic/user", data);
    }
}
