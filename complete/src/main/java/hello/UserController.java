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

import repository.UserRepository;
import model.User;

//Rest for transferring data
@RestController
public class UserController {	
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
    	//userRepo.validateUser(id);
    	return userRepo.userById(id);
    }
    
    //Insert new user(json) 
    @RequestMapping(value="/addUser",method=RequestMethod.POST)
    public @ResponseBody int addUserJson(@RequestBody User user) {
    	String name = user.getUserName();
    	String password = user.getPassword();
    	password = user.hashPassword(password);
    	String mail = user.getMail();
      //Return 1 when it's ok
    	return userRepo.addUser(name, password, mail);
    }
    
    @GetMapping("/findUser/{userName}")
    public @ResponseBody List<User> findUser(@PathVariable("userName") String userName) {
      //Return 1 when it's ok
    	return userRepo.findUser(userName);
    }
    
    @GetMapping("/findUserId/{userName}")
    public @ResponseBody User findUserId(@PathVariable("userName") String userName) {
      //Return 1 when it's ok
    	return userRepo.findUserId(userName);
    }
    
    @GetMapping("/isUserExist/{userName}")
    public @ResponseBody String isUserExist(@PathVariable("userName") String userName) {
    	return userRepo.isUserExist(userName);
    }
}
