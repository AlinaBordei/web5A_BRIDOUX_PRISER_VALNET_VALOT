package hello;

import model.Message;
import model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import repository.MessageRepository;
import repository.UserRepository;

import java.util.List;

import javax.inject.Inject;


@Controller
public class MessageController {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Inject
    	MessageRepository messageRepo;
	@Inject
		SimpMessagingTemplate simpMessagingTemplate;
	@Inject
		UserRepository userRepo;

    @MessageMapping("/message")
    public Message transferMessage(Message message) throws Exception {
    	int conversationID = message.getConversationID();
    	String msg = message.getMessage();
    	int userID = message.getUserID();
    	messageRepo.addMessage(conversationID, msg, userID);
    	List<User> group = userRepo.findUsersByConversationId(conversationID);
    	for(User user : group) {
    		simpMessagingTemplate.convertAndSend("/topic/message/"+user.getUserId(), message);
    		log.info("Message sent to: /topic/message/"+user.getUserId());
    	}
        return message;
    }
    
  //Get messages by conversation id
    @GetMapping("/messageByConversationId/{id}")
    public @ResponseBody List<Message> messageByConversationId(@PathVariable("id") Integer id) {
    	messageRepo.messageByConversationId(id);
    	return messageRepo.messageByConversationId(id);
    } 

}




