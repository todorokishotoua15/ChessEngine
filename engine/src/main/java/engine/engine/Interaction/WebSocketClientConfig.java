package engine.engine.Interaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
// import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class WebSocketClientConfig {

    @Bean
    public WebSocketStompClient stompClinet() {
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(jacksonMessageConverter());
        return stompClient;
    }

    @Bean
    public MessageConverter jacksonMessageConverter() {
        return new MappingJackson2MessageConverter();
    }
}
