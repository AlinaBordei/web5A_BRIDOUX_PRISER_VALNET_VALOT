package model;

public class Exchanges {

	protected int id_users;
	protected int IDConversation;
	
	public Exchanges(){
		this.id_users = 0;
		this.IDConversation = 0;

	}
	
	public Exchanges(int id_users, int IDConversation) {
		this.id_users = id_users;
		this.IDConversation = IDConversation;
	}
	
	public void setIdu(int iduser) {
        this.id_users = iduser;
    }
	
	public void setIde(int idExchanges) {
        this.IDConversation = idExchanges;
    }
	
	public int getIdu() {
        return this.id_users;
    }
	
	public int getIde() {
        return this.IDConversation;
    }
}
