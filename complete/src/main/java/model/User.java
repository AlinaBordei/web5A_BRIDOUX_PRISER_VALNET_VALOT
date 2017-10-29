package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

	protected int userId;
	protected String userName;
	protected String mail;

	public User() {
		this.userId = 0;
		this.userName = null;
		this.mail = null;
	}

	public User(int userId, String username, String mail) {
		this.userId = userId;
		this.userName = username;
		this.mail = mail;
	}

	@Override
	public String toString() {
		return String.format("User[userId=%d, username='%s', mail='%s']", userId, userName, mail);
	}
	
	public String hashPassword(String password) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			String encryptedString = new String(messageDigest.digest());
			return encryptedString;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "error";
		}
		
		
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

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return this.mail;
	}
}
