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

	public List<Message> messageById(int messageId) {
		List<Message> exchange = new ArrayList<Message>();

		exchange = jdbcTemplate.query("SELECT * FROM message WHERE IDMessage = ?", new Object[] { messageId },
				(rs, rowNum) -> new Message(rs.getInt("messageId")));

		return exchange;
	}
}
