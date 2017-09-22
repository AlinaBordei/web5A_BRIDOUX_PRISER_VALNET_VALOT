package hello;

import java.util.List;


import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import repository.UserConversationRepository;
import model.UserConversation;

//Rest for transferring data
@RestController
public class UserConversationController {	
	@Inject
	UserConversationRepository userConversationRepo;
	
	//Get all users
    @GetMapping("/allUsersConversations")
    public @ResponseBody List<UserConversation> allUserConversation() {
    	return userConversationRepo.allUserConversation();
    }
    
    //Get user by id
    @GetMapping("/userConversationByUser/{id}")
    public @ResponseBody List<UserConversation> userConversationByUser(@PathVariable("id") Integer id) {
    	return userConversationRepo.userConversationByUser(id);
    }
    
  //Get user by id
    @GetMapping("/userConversationById/{id}")
    public @ResponseBody List<UserConversation> userConversationById(@PathVariable("id") Integer id) {
    	return userConversationRepo.userConversationById(id);
    }
    
  //Insert new user(json) 
    @RequestMapping(value="/addUserConversation",method=RequestMethod.POST)
    public @ResponseBody int addUserJson(@RequestBody UserConversation userConversation) {
    	int userId = userConversation.getUserId();
    	int conversationId = userConversation.getConversationId();
      //Return 1 when it's ok
    	return userConversationRepo.addUserConversation(userId, conversationId);
    }
}
