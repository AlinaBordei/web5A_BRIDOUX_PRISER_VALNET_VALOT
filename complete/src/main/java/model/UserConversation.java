package model;

public class UserConversation {

	protected int userId;
	protected int conversationId;
	
	public UserConversation(){
		this.userId = 0;
		this.conversationId = 0;

	}
	
	public UserConversation(int userId, int conversationId) {
		this.userId = userId;
		this.conversationId = conversationId;
	}
	
	public void setUserId(int idUser) {
        this.userId = idUser;
    }
	
	public void setConversationId(int idConversation) {
        this.conversationId = idConversation;
    }
	
	public int getUserId() {
        return this.userId;
    }
	
	public int getConversationId() {
        return this.conversationId;
    }
}
