package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
