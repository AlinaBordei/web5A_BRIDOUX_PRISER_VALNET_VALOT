package repository;

import java.util.ArrayList;
import java.util.Date;
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

		messages = jdbcTemplate.query("SELECT * FROM message WHERE id_conversation = ?", new Object[] { conversationID },
				(rs, rowNum) -> new Message(rs.getInt("IDMessage"), rs.getInt("id_conversation"), rs.getString("message"), rs.getDate("date"), rs.getInt("userID")));

		return messages;
	}
	
	public int addMessage(int conversationID, String message, Date datetime, int userID) {
		return jdbcTemplate.update("INSERT INTO message(message, date, id_conversation, userID) VALUES(?,?,?,?)", message, datetime, conversationID, userID);
	}
}
