package repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Conversation;

@Repository
@Named
public class ConversationRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public ConversationRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Conversation> allConversation() {
		List<Conversation> Conversation = new ArrayList<Conversation>();

		Conversation = jdbcTemplate.query("SELECT * FROM conversation",
				(rs, rowNum) -> new Conversation(rs.getInt("IDConversation")));

		return Conversation;
	}

	public List<Conversation> conversationById(int conversationId) {
		List<Conversation> conversation = new ArrayList<Conversation>();

		conversation = jdbcTemplate.query("SELECT * FROM conversation WHERE IDConversation = ?", new Object[] { conversationId },
				(rs, rowNum) -> new Conversation(rs.getInt("IDConversation")));

		return conversation;
	}
}
