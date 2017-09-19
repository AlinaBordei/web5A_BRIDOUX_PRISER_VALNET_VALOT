package model;

public class Exchanges {

	protected int userId;
	protected int conversationId;
	
	public Exchanges(){
		this.userId = 0;
		this.conversationId = 0;

	}
	
	public Exchanges(int userId, int conversationId) {
		this.userId = userId;
		this.conversationId = conversationId;
	}
	
	public void setIdu(int iduser) {
        this.userId = iduser;
    }
	
	public void setIde(int idExchanges) {
        this.conversationId = idExchanges;
    }
	
	public int getIdu() {
        return this.userId;
    }
	
	public int getIde() {
        return this.conversationId;
    }
}
