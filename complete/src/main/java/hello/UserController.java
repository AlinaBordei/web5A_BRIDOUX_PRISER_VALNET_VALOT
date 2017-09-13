package hello;

import java.util.List;

import javax.inject.Inject;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import repository.UserRepository;
import model.User;

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
    	return userRepo.userById(id);
    }
    
    //Add user
    @GetMapping("/addUser/{name}/{password}/{mail}")
    public @ResponseBody int addUser(@PathVariable("name") String name,@PathVariable("password") String password, @PathVariable("mail") String mail) {
    	//Return 1 when it's ok
    	return userRepo.addUser(name, password, mail);
    }
}
