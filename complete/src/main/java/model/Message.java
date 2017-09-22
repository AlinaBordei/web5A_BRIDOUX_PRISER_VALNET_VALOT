package model;

import java.util.Date;


public class Message {

	///////////////////// VAR /////////////////////
	protected int messageId;
	protected int conversationID;
	protected String message;
	protected Date datetime;
	protected int userID;

	public Message() {
		this.messageId = 0;
		this.conversationID = 0;
		this.message = null;
		this.datetime = null;
		this.userID = 0;
	}

	public Message(int messageId, int conversationID, String message, Date datetime, int userID) {
		this.messageId = messageId;
		this.conversationID = conversationID;
		this.message = message;
		this.datetime = datetime;
		this.userID = userID;
	}

	///////////////////// SET /////////////////////
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public void setConversationID(int conversationID) {
		this.conversationID = conversationID;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	///////////////////// GET /////////////////////

	public int getMessageId() {
		return this.messageId;
	}

	public int getConversationID() {
		return this.conversationID;
	}

	public String getMessage() {
		return this.message;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public int getUserID() {
		return this.userID;
	}
	///////////////////// END /////////////////////
}