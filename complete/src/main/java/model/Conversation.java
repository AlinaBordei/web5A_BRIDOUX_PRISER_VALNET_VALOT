package model;

public class Conversation {

	protected int conversationId;

	public Conversation() {
		this.conversationId = 0;

	}

	public Conversation(int conversationId) {
		this.conversationId = conversationId;
	}

	public void setIdConversation(int idConversation) {
		this.conversationId = idConversation;
	}

	public int getIdConversation() {
		return this.conversationId;
	}
}
