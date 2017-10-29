package repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Message;

@Repository
@Named
public class MessageRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public MessageRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Message> messageByConversationId(int conversationID) {
		List<Message> messages = new ArrayList<Message>();

		messages = jdbcTemplate.query("SELECT message.*, user.name as username FROM message "
				+ "INNER JOIN user ON message.userID = user.id "
				+ "WHERE id_conversation = ? order by date ASC", new Object[] { conversationID },
				(rs, rowNum) -> new Message(rs.getInt("IDMessage"), rs.getInt("id_conversation"), rs.getString("message"), rs.getTimestamp("date"), rs.getInt("userID"), rs.getString("username")));

		return messages;
	}
	
        
	public int addMessage(int conversationID, String message, int userID) {
		return jdbcTemplate.update("INSERT INTO message(message, id_conversation, userID) VALUES(?,?,?)", message, conversationID, userID);
	}
	
}
