package repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Message;
import model.User;

@Repository
@Named
public class MessageRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public MessageRepository(JdbcTemplate jdbcTemplate2) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Message> messageByConversation(int conversationID) {
		List<Message> exchange = new ArrayList<Message>();

		exchange = jdbcTemplate.query("SELECT * FROM message WHERE conversationID = ?", new Object[] { conversationID },
				(rs, rowNum) -> new Message(rs.getInt("messageId"), rs.getInt("conversationID"), rs.getString("message"), rs.getDate("datetime"), rs.getInt("userID")));

		return exchange;
	}
}
