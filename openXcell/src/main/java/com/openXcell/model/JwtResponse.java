package com.openXcell.model;

public class JwtResponse {
	
	private String jwt;
	private int userId;
	private String username;
	private String email;
	
	public JwtResponse(String jwt, int id, String username, String email) {
		super();
		this.jwt = jwt;
		this.userId = id;
		this.username = username;
		this.email = email;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
