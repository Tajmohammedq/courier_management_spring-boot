package jpapackage.dto;

public class ErrorDto {

	private int status;
	private String message;
	
	public ErrorDto(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	
}
