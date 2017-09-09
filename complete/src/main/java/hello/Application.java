package hello;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.*;

//@ImportResource("classpath:spring/application-config.xml")
@PropertySource(value = { "classpath:application.properties" })

@SpringBootApplication
public class Application implements CommandLineRunner{

    public static void main(String[] args) {
    	/*System.out.println("LE TEST COMMENCE ICI !!");
    	UserQuery testQuery = new UserQuery();
    	User user[] = testQuery.select();
    	int i = 0;
    	
    	for(i = 0; i<user.length; i++) {
    		System.out.println("> " + user[i]);
    	}
    	*/
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        log.info("Creating tables");

        //jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        /*jdbcTemplate.execute("CREATE TABLE user IF NOT EXISTS(" +
                " IDUser INT NULL AUTO_INCREMENT,\r\n" + 
                "  username VARCHAR(16) NOT NULL,\r\n" + 
                "  password VARCHAR(32) NOT NULL,\r\n" + 
                "    PRIMARY KEY (IDUser))");
		
        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting user record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO user(name, password) VALUES (?,?)", splitUpNames);
		*/
        System.out.println("JE SUIS AU DEBUT DE MA FONCTION !!");
        jdbcTemplate.query(
                "SELECT * FROM user",
                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"))
        ).forEach(user -> System.out.println(user.toString()));
        System.out.println("JE SUIS A LA FIN DE MA FONCTION !!");
    }

}
