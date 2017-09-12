package model;

public class User {

	protected int id_user;
	protected String username;
	protected String passsword;
	protected String mail;
	
	public User(){
		this.id_user = 0;
		this.username = null;
		this.passsword = null;
		this.mail = null;
	}
	
	public User(int id_user, String username, String passsword, String mail) {
		this.id_user = id_user;
		this.username = username;
		this.passsword = passsword;
		this.mail = mail;
	}
	
	@Override
    public String toString() {
        return String.format(
                "User[id=%d, userName='%s', psswd='%s', mail='%s']",
                id_user, username, passsword,mail);
    }
	
	public void setId(int id) {
        this.id_user = id;
    }
	
	public int getId() {
        return this.id_user;
    }
	
	public void setName(String name) {
        this.username = name;
    }
	
	public String getName() {
        return this.username;
    }
	
	public void setPwd(String pwd) {
        this.passsword = pwd;
    }
	
	public String getPwd() {
        return this.passsword;
    }
	
	public void setMail(String mail) {
        this.mail = mail;
    }
	
	public String getMail() {
        return this.mail;
    }
}
