package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class Message {

	///////////////////// VAR /////////////////////
	protected int messageId;
	protected int conversationID;
	protected String message;
	//protected Date datetime;
	protected Timestamp datetime;
	protected String sdf;
	//protected String datetime;
	protected int userID;
	protected String username;

	public Message() {
		this.messageId = 0;
		this.conversationID = 0;
		this.message = null;
		//this.datetime = new Date();
		this.datetime = new Timestamp(System.currentTimeMillis());
		this.userID = 0;
	}

	public Message(int messageId, int conversationID, String message, Timestamp datetime, int userID, String username) {
		this.messageId = messageId;
		this.conversationID = conversationID;
		this.message = message;
		this.datetime = datetime;
		this.userID = userID;
		this.username = username;
		setSdf(datetime);
	}

	///////////////////// SET /////////////////////
	public void setUsername(String username) {
		this.username = username;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public void setConversationID(int conversationID) {
		this.conversationID = conversationID;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/*public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}*/
	
	public void setSdf(Timestamp datetime) {
		this.sdf = new SimpleDateFormat("dd/MM HH:mm").format(datetime.getTime());
	}
	
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	///////////////////// GET /////////////////////
	
	public String getUsername() {
		return username;
	}

	public int getMessageId() {
		return this.messageId;
	}

	public int getConversationID() {
		return this.conversationID;
	}

	public String getMessage() {
		return this.message;
	}
	
	public String getSdf() {
		return this.sdf;
	}

	public String getDatetime() {
		return new SimpleDateFormat("dd/MM HH:mm").format(datetime);
	}

	public int getUserID() {
		return this.userID;
	}
	///////////////////// END /////////////////////
}