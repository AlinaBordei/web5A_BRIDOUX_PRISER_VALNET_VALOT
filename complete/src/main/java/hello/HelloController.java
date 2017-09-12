package hello;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import repository.UserRepository;

@Controller
public class HelloController {
    
	/*@Inject
	UserRepository userRepo;
	*/
    @RequestMapping("/")
    public String index() {
    	//userRepo.allUsers();
    	return "index";
    }
}
