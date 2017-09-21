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
	public void setIDM(int messageId) {
		this.messageId = messageId;
	}

	public void setIDC(int conversationID) {
		this.conversationID = conversationID;
	}

	public void setTxt(String message) {
		this.message = message;
	}

	public void setDTM(Date datetime) {
		this.datetime = datetime;
	}

	public void setIDU(int userID) {
		this.userID = userID;
	}
	///////////////////// GET /////////////////////

	public int getIdm() {
		return this.messageId;
	}

	public String getIDC() {
		return this.message;
	}

	public int getTxt() {
		return this.messageId;
	}

	public String getDTM() {
		return this.message;
	}

	public String getIDU() {
		return this.message;
	}
	///////////////////// END /////////////////////
}