package hello;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import repository.ConversationRepository;
import model.Conversation;

// TODO
//
//

//Rest for transferring data
@RestController
public class ConversationController {	
	@Inject
	ConversationRepository convRepo;
	
	//Get all conversation
    @GetMapping("/conversation")
    public @ResponseBody List<Conversation> allConversation() {
    	return convRepo.allConversation();
    }

    //Get conversation by id
    @GetMapping("/conversationById/{id}")
    public @ResponseBody List<Conversation> conversationById(@PathVariable("id") Integer id) {
    	return convRepo.conversationById(id);
    }
}
