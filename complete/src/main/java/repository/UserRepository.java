package repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
@Named
public class UserRepository {
	
	@Autowired
    JdbcTemplate jdbcTemplate;

	public UserRepository(JdbcTemplate jdbcTemplate2) {
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
	
	public List<User> userById(int id) {
		List<User> user = new ArrayList<User>();
		
		user = jdbcTemplate.query(
                "SELECT * FROM user WHERE id = ?", new Object[] { id },
                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("mail"))
        );
		
		return user;
	}
	
	public void addUser() {
		
	}
}
