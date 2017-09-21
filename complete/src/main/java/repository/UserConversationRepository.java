package repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.UserConversation;

@Repository
@Named
public class UserConversationRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public UserConversationRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<UserConversation> allUserConversation() {
		List<UserConversation> userConversation = new ArrayList<UserConversation>();

		userConversation = jdbcTemplate.query("SELECT * FROM user_conversation",
				(rs, rowNum) -> new UserConversation(rs.getInt("userID"), rs.getInt("conversationID")));

		return userConversation;
	}
	
	public List<UserConversation> userConversationByUser(int userId) {
		List<UserConversation> userConversation = new ArrayList<UserConversation>();

		userConversation = jdbcTemplate.query("SELECT * FROM user_conversation WHERE userID = ?", new Object[] { userId },
				(rs, rowNum) -> new UserConversation(rs.getInt("userID"), rs.getInt("conversationID")));

		return userConversation;
	}

	public List<UserConversation> userConversationById(int conversationId) {
		List<UserConversation> userConversation = new ArrayList<UserConversation>();

		userConversation = jdbcTemplate.query("SELECT * FROM user_conversation WHERE conversationID = ?", new Object[] { conversationId },
				(rs, rowNum) -> new UserConversation(rs.getInt("userID"), rs.getInt("conversationID")));

		return userConversation;
	}
}
