package model;

public class User {

	protected int id_user;
	protected String username;
	protected String passsword;
	
	public User(){
		this.id_user = 0;
		this.username = null;
		this.passsword = null;
	}
	
	public User(int id_user, String username, String passsword) {
		this.id_user = id_user;
		this.username = username;
		this.passsword = passsword;
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
}
