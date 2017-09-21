package hello;

import java.util.List;

import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import repository.MessageRepository;
import model.Message;

//Rest for transferring data
@RestController
public class MessageController {	
	@Inject
	MessageRepository messageRepo;

    //Get messages by conversation id
    @GetMapping("/messageByConversationId/{id}")
    public @ResponseBody List<Message> messageByConversationId(@PathVariable("id") Integer id) {
    	messageRepo.messageByConversationId(id);
    	return messageRepo.messageByConversationId(id);
    }
    
}
