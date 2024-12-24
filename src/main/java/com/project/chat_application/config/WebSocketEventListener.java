
package com.project.chat_application.config;

import com.project.chat_application.chat.ChatMessage;
import com.project.chat_application.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
//@RequiredArgsConstructor
//@Slf4j
public class WebSocketEventListener {
    private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);

    private final SimpMessageSendingOperations messageTemplate;

    // Manually define the constructor for dependency injection
    public WebSocketEventListener(SimpMessageSendingOperations messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        // Wrap the event message to extract session attributes
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        // If username exists, log and notify others about the disconnection
        if (username != null) {
            log.info("User Disconnected: {}", username);

            // Build the ChatMessage to notify others
            ChatMessage chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)  // Set message type (e.g., LEAVE)
                    .sender(username)         // Set the sender's username
                    .build();                 // Build the ChatMessage

            // Send message to all subscribers
            messageTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}












//package com.project.chat_application.config;
//
//import com.project.chat_application.chat.ChatMessage;
//import com.project.chat_application.chat.MessageType;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.event.EventListener;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.messaging.SessionDisconnectEvent;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class WebSocketEventListener {
//
//
//    private final SimpMessageSendingOperations messageTemplate;
//
//
//    @EventListener
//    public void handleWebSocketDisconnectListener(
//            SessionDisconnectEvent event
//    ){
////        To be implemented
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String username =(String) headerAccessor.getSessionAttributes().get("username");
//        if (username !=null){
//            log.info("User Disconnected: {}",username);
//            var chatMessage = ChatMessage.builder()
//                    .type(MessageType.LEAVE)
//                    .sender(username)
//                    .build();
//            messageTemplate.convertAndSend("/topic/public",chatMessage);
//        }
//
//    }
//}
