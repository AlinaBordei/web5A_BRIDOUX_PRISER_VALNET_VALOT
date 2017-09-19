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

import repository.UserRepository;
import model.User;

// TODO
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

//Rest for transferring data
@RestController
public class ConversationController {	
	@Inject
	UserRepository userRepo;
	
	//Get all users
    @GetMapping("/users")
    public @ResponseBody List<User> allUsers() {
    	return userRepo.allUsers();
    }
    
    //Get user by id
    @GetMapping("/usersById/{id}")
    public @ResponseBody User userById(@PathVariable("id") Integer id) {
    	userRepo.validateUser(id);
    	return userRepo.userById(id);
    }
    
    
    
    
    
    //Add user
    @GetMapping("/addUser/{name}/{password}/{mail}")
    public @ResponseBody int addUser(@PathVariable("name") String name,@PathVariable("password") String password, @PathVariable("mail") String mail) {
    	//Return 1 when it's ok
    	return userRepo.addUser(name, password, mail);
    }
    
    //To test
    @PostMapping("/addUser2")
    public @ResponseBody int addUserJson(@RequestBody User user) {
    	String name = user.getUserName();
    	String password = user.getPassword();
    	String mail = user.getMail();
      //Return 1 when it's ok
    	return userRepo.addUser(name, password, mail);
    }
}
