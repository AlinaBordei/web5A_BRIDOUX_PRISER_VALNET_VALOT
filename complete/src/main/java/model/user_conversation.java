package model;

public class user_conversation {

	protected int userId;
	protected int conversationId;
	
	public user_conversation(){
		this.userId = 0;
		this.conversationId = 0;

	}
	
	public user_conversation(int userId, int conversationId) {
		this.userId = userId;
		this.conversationId = conversationId;
	}
	
	public void setIdu(int iduser) {
        this.userId = iduser;
    }
	
	public void setIde(int idConversation) {
        this.conversationId = idConversation;
    }
	
	public int getIdu() {
        return this.userId;
    }
	
	public int getIde() {
        return this.conversationId;
    }
}
