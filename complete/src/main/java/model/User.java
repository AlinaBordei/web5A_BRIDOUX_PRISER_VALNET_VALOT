package model;

public class User {

	protected int userId;
	protected String userName;
	protected String password;
	protected String mail;

	public User() {
		this.userId = 0;
		this.userName = null;
		this.password = null;
		this.mail = null;
	}

	public User(int userId, String username, String password, String mail) {
		this.userId = userId;
		this.userName = username;
		this.password = password;
		this.mail = mail;
	}

	@Override
	public String toString() {
		return String.format("User[userId=%d, username='%s', password='%s', mail='%s']", userId, userName, password,
				mail);
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return this.mail;
	}
}
