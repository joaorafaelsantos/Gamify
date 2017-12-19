package com.gamify.model;

public class Error {

	private int code;
	private String message;
	private String link;
	private int http_status;
	
	public Error(int code, String message, String link, int http_status) {
		super();
		this.code = code;
		this.message = message;
		this.link = link;
		this.http_status = http_status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public int getHttp_status() {
		return http_status;
	}

	public void setHttp_status(int http_status) {
		this.http_status = http_status;
	}
	
	
	
	

}
