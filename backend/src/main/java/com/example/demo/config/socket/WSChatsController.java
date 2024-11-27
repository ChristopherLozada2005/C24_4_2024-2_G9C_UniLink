package com.example.demo.config.socket;

import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class WSChatsController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public WSChatsController(SimpMessagingTemplate messagingTemplate, MessageRepository messageRepository, UserRepository userRepository) {
        this.messagingTemplate = messagingTemplate;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @MessageMapping("/chat/{id_sender}/{id_receiver}")
    public void getMessage(@PathVariable Long id_sender, @PathVariable Long id_receiver, @Payload Message message) {

        try {
            Optional<User> receiver = userRepository.findById(id_receiver);
            Optional<User> sender = userRepository.findById(id_sender);

            if (receiver.isPresent() && sender.isPresent()) {
                message.setReceiver(receiver.get());
                message.setSender(sender.get());
                messageRepository.save(message);

                messagingTemplate.convertAndSendToUser(
                        id_receiver.toString(),
                        "/queue/messages",
                        message
                );

                System.out.println("Message received: " + message);
            } else {
                System.err.println("Sender or receiver does not exist");
            }
        } catch (Exception e) {
            System.err.println("Error handling message: " + e.getMessage());
        }
    }

}
