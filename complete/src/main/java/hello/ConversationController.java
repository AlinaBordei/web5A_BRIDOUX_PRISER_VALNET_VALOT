package hello;

import java.util.List;

import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @GetMapping("/ConversationById/{id}")
    public @ResponseBody List<Conversation> ConversationById(int id) {
    	return convRepo.ConversationById(id);
    }
}
