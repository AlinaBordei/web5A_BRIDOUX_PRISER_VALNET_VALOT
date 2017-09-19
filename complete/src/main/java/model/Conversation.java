package model;

public class Conversation {

	protected int conversationId;

	public Conversation() {
		this.conversationId = 0;

	}

	public Conversation(int conversationId) {
		this.conversationId = conversationId;
	}

	public void setIde(int idConversation) {
		this.conversationId = idConversation;
	}

	public int getIde() {
		return this.conversationId;
	}
}
