package repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Conversation;
import model.User;
import model.user_conversation;

@Repository
@Named
public class user_ConversationRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public user_ConversationRepository(JdbcTemplate jdbcTemplate2) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<user_conversation> alluser_conversation() {
		List<user_conversation> User_conversation = new ArrayList<user_conversation>();

		User_conversation = jdbcTemplate.query("SELECT * FROM conversation",
				(rs, rowNum) -> new user_conversation(rs.getInt("conversationId"), rs.getInt("userId")));

		return User_conversation;
	}
	
	public List<user_conversation> ConversationByUser(int userId) {
		List<user_conversation> User_conversation = new ArrayList<user_conversation>();

		User_conversation = jdbcTemplate.query("SELECT * FROM conversation WHERE userId = ?", new Object[] { userId },
				(rs, rowNum) -> new user_conversation(rs.getInt("conversationId"), rs.getInt("userId")));

		return User_conversation;
	}

	public List<user_conversation> ConversationById(int conversationId) {
		List<user_conversation> User_conversation = new ArrayList<user_conversation>();

		User_conversation = jdbcTemplate.query("SELECT * FROM conversation WHERE conversationId = ?", new Object[] { conversationId },
				(rs, rowNum) -> new user_conversation(rs.getInt("conversationId"), rs.getInt("userId")));

		return User_conversation;
	}
}
