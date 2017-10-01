package hello;

import model.Message; 
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import repository.MessageRepository;
import javax.inject.Inject;
import java.util.Date;
//import java.util.List;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;


@Controller
public class MessageController {
	
	@Inject
    	MessageRepository messageRepo;

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public Message transferMessage(Message message) throws Exception {
    	messageRepo.addMessage(message);
        return message;
    }

}

/*
//Get messages by conversation id
@GetMapping("/messageByConversationId/{id}")
public @ResponseBody List<Message> messageByConversationId(@PathVariable("id") Integer id) {
	messageRepo.messageByConversationId(id);
	return messageRepo.messageByConversationId(id);
} */

