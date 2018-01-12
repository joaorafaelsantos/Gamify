package com.gamify.model;

public class Error {

	private String errorID;
	private String message;
	private String link;
	private String http_status;
	
	public Error() {}
	
	public Error(String errorID, String message, String link, String http_status) {
		super();
		this.errorID = errorID;
		this.message = message;
		this.link = link;
		this.http_status = http_status;
	}

	public String getErrorID() {
		return errorID;
	}
	
	public void setErrorID(String errorID) {
		this.errorID = errorID;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getHttp_status() {
		return http_status;
	}

	public void setHttp_status(String http_status) {
		this.http_status = http_status;
	}
}
