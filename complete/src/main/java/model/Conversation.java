package model;

public class Conversation {

	protected int conversationId;
	protected String userNames;
	private Message lastMessage;

	public Conversation() {
		this(0, null);
	}

	public Conversation(int conversationId) {
		this(conversationId, null);
	}

	public Conversation(int conversationId, String userNames) {
		this.conversationId = conversationId;
		this.userNames = userNames;
	}

	public void setConversationId(int idConversation) {
		this.conversationId = idConversation;
	}

	public int getConversationId() {
		return this.conversationId;
	}

	public String getUserNames() {
		return userNames;
	}

	public Message getLastMessage() {
		return lastMessage;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public Conversation setLastMessage(Message message) {
		this.lastMessage = message;
		return this;
	}
}
