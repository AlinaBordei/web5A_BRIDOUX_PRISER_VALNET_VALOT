package hello;

import model.Message;
import model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
    	log.info("L'id conv vaut: " + conversationID);
    	String msg = message.getMessage();
    	int userID = message.getUserID();
    	List<User> group = userRepo.findUsersByConversationId(conversationID);
 
    	boolean isUserInGroup = false;
    	for(User user : group) {
    		if(userID == user.getUserId()){
    			message.setUsername(user.getUserName());
    			isUserInGroup = true;
    		}
    	}
    	if (!isUserInGroup) {
    		throw new Exception("L'user "+ userID+ " ne fait pas partie de la conversation: "+conversationID);
    	}
    	
    	messageRepo.addMessage(conversationID, msg, userID);
    	log.info(group.toString());
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




