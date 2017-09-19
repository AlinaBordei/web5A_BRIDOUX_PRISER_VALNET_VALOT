package model;

public class Message {

	protected int messageId;
	
	public Message(){
		this.messageId = 0;
	}
	
	public Message(int messageId) {
		this.messageId = messageId;
	}
	
	public void setIdm(int messageId) {
        this.messageId = messageId;
    }
	
	public int getIdm() {
        return this.messageId;
    }
}
