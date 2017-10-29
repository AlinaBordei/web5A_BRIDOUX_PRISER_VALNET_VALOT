package repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Conversation;
import model.Message;

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
	
	public int addConversation() { 
		 return jdbcTemplate.update("INSERT INTO conversation() VALUES()");

            /*int idConv = jdbcTemplate.queryForInt("SELECT MAX(IDConversation) FROM conversation")+1;
            idConv = jdbcTemplate.update("INSERT INTO conversation() VALUES(?)", idConv);
            List<String> users = Arrays.asList(str.split("\\s*;\\s*")); 
            for (String user : users) {
                jdbcTemplate.update("INSERT INTO user_conversation(conversationID, userID) VALUES(?, ?)", idConv, user);
            }
            return idConv;*/
	}
	
	public int lastConversationCreated() {
		List<Conversation> conversation = new ArrayList<Conversation>();

		conversation = jdbcTemplate.query("SELECT MAX(IDConversation) FROM conversation",
				(rs, rowNum) -> new Conversation(rs.getInt("MAX(IDConversation)")));

		return conversation.get(0).getConversationId();
	}

	public List<Conversation> findByUserId(Integer id) {
		List<Conversation> conversations = new ArrayList<Conversation>();

		String req = "SELECT conversation.IDConversation, group_concat(user.name) as userNames FROM conversation "
				+ "INNER JOIN user_conversation my_uc ON conversation.IDConversation=my_uc.conversationID AND my_uc.userID = ? "
				+ "INNER JOIN user_conversation others_uc ON conversation.IDConversation=others_uc.conversationID AND others_uc.userId != my_uc.userID "
				+ "LEFT JOIN user ON others_uc.userID=user.id "
				+ "GROUP BY conversation.IDConversation";
		//System.out.println(req);
		conversations = jdbcTemplate.query(req, new Object[] { id },
				(rs, rowNum) -> new Conversation(rs.getInt("IDConversation"), rs.getString("userNames")));
		
		for (Conversation conversation : conversations) {
			jdbcTemplate.query("SELECT * FROM message WHERE message.id_conversation = ? ORDER BY message.date DESC LIMIT 1", new Object[] { conversation.getConversationId() }, 
					(rs, rowNum) -> conversation.setLastMessage(
							new Message(rs.getInt("IDMessage"), rs.getInt("id_conversation"), rs.getString("message"), rs.getTimestamp("date"), rs.getInt("userID"), null)
			));
		}
		
		return conversations;
	}
}
