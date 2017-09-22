package hello;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
  //Insert new message(json) 
    @RequestMapping(value="/addMessage",method=RequestMethod.POST)
    public @ResponseBody int addMessage(@RequestBody Message msg) {
    	int conversationID = msg.getConversationID();
    	String message = msg.getMessage();
    	Date datetime = msg.getDatetime();
    	int userID = msg.getUserID();
      //Return 1 when it's ok
    	return messageRepo.addMessage(conversationID, message, datetime, userID);
    }
    
}
