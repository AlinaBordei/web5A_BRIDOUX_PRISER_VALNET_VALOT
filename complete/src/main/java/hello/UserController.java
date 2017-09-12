package hello;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import repository.UserRepository;
import model.User;

@RestController
public class UserController {	
	@Inject
	UserRepository userRepo;
	
    @GetMapping("/users")
    public @ResponseBody List<User> allUsers() {
    	return userRepo.allUsers();
    }
}
