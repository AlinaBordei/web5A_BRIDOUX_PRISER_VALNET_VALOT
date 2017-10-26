package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import model.Login;
import model.User;

@Repository
@Named
public class UserRepository {
	
	@Autowired
    JdbcTemplate jdbcTemplate;

	public UserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<User> allUsers() {
	    List<User> users = new ArrayList<User>();
	    
	    users = jdbcTemplate.query(
                "SELECT * FROM user",
                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("mail"))
        );
	    
	    return users;
	}
	
	public List<User> findUser(String nameBegining) {
	    List<User> users = new ArrayList<User>();
	    nameBegining = nameBegining + "%";
	    
	    users = jdbcTemplate.query(
                "SELECT * FROM user WHERE name like ?", new Object[] { nameBegining },
                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("mail"))
        );
	    
	    return users;
	}
	
	public User findUserId(String nameBegining) {
	    List<User> users = new ArrayList<User>();
	    
	    users = jdbcTemplate.query(
                "SELECT * FROM user WHERE name = ?", new Object[] { nameBegining },
                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("mail"))
        );
	    
	    return users.get(0);
	}
	
	public User userById(int id) {
		List<User> user = new ArrayList<User>();
		
		user = jdbcTemplate.query(
                "SELECT * FROM user WHERE id = ?", new Object[] { id },
                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("mail"))
        );
		
		return user.get(0);
	}
	
	public int addUser(String name, String password, String mail) {
		return jdbcTemplate.update("INSERT INTO user(name, password, mail) VALUES(?,?,?)", name, password, mail);
	}
	
	public String isUserExist(String name) {
		
	    List<User> users = new ArrayList<User>();
	    name = name + "%";
	    //try {
		    users = jdbcTemplate.query(
	                "SELECT * FROM user WHERE name like ?", new Object[] { name },
	                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("mail"))
	        );
		    if(users.size() == 0) {
		    	return "false";
		    }else {
		    	return "true";
		    }
	    
	}
	
public int authentification(User user) {
		
	    List<User> users = new ArrayList<User>();
	    //String pwd = user.hashPassword(user.getPassword());
	    String pwd = user.getPassword();
	    String mail = user.getMail();
	    //try {
		    users = jdbcTemplate.query(
	                "SELECT * FROM user WHERE mail = ? and password = ?", new Object[] { mail, pwd },
	                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("mail"))
	        );
		    if(users.size() == 0) {
		    	return 0;
		    }else {
		    	return users.get(0).getUserId();
		    }
	    
	}
	
	/*public void validateUser(int id) {
		List<User> user = new ArrayList<User>();
		User test = new User();
		
		user = jdbcTemplate.query(
                "SELECT * FROM user WHERE id = ?", new Object[] { id },
                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("mail"))
        );
		
		test = user.get(0);
		Optional.ofNullable(test).orElseThrow(
				() -> new UserNotFoundException(id));
	}*/
	
	public User validateUser(Login login) {
		
		String sql = "select * from user where name='" + login.getUsername() + "' and password='" + login.getPassword()+ "'";
	    List<User> users = jdbcTemplate.query(sql, new UserMapper());
	    
	    return users.size() > 0 ? users.get(0) : null;
	}
	
	class UserMapper implements RowMapper<User> {
		  
		public User mapRow(ResultSet rs, int arg1) throws SQLException {
			
		    User user = new User();
		    
		    user.setUserName(rs.getString("name"));
		    user.setPassword(rs.getString("password"));
		    user.setMail(rs.getString("mail"));
		    
		    return user;
	    }
	}

	public List<User> findUsersByConversationId(int conversationID) {
		List<User> users = new ArrayList<User>();
	    
	    users = jdbcTemplate.query(
                "SELECT * FROM user INNER JOIN user_conversation ON user.id=user_conversation.userID "
                + "WHERE conversationID =  ?", new Object[] { conversationID },
                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("mail"))
        );
	    
	    return users;
		
	}
}
