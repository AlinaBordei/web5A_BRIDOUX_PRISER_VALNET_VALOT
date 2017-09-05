package hello;

//import java.util.ArrayList;
//import java.util.List;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    
    //ArrayList list = new ArrayList();
    
    @RequestMapping("/")
    public String index() {
        //list.add("toto");
        return "index";
    }
}
