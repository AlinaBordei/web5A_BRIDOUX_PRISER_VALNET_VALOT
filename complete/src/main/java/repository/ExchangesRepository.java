package repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Exchanges;
import model.User;

@Repository
@Named
public class ExchangesRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public ExchangesRepository(JdbcTemplate jdbcTemplate2) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Exchanges> allExchanges() {
		List<Exchanges> exchanges = new ArrayList<Exchanges>();

		exchanges = jdbcTemplate.query("SELECT * FROM conversation",
				(rs, rowNum) -> new Exchanges(rs.getInt("conversationId"), rs.getInt("userId")));

		return exchanges;
	}

	public List<Exchanges> exchangesById(int conversationId) {
		List<Exchanges> exchange = new ArrayList<Exchanges>();

		exchange = jdbcTemplate.query("SELECT * FROM conversation WHERE userId = ?", new Object[] { conversationId },
				(rs, rowNum) -> new Exchanges(rs.getInt("conversationId"), rs.getInt("userId")));

		return exchange;
	}
}
