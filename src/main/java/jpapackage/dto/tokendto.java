package jpapackage.dto;

import lombok.Data;

@Data
public class tokendto {
	
	private String message;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public String getRefresh() {
		return refresh;
	}
	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}
	
	String token;

	String refresh;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
