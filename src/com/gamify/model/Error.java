package com.gamify.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class Error {

	private String errorID;
	private String message;
	private String link;
	private String http_status;

	public Error() {
	}

	public Error(String errorID, String message, String link, String http_status) {
		super();
		this.errorID = errorID;
		this.message = message;
		this.link = link;
		this.http_status = http_status;
	}

	@XmlElement(name = "errorID")
	public String getErrorID() {
		return errorID;
	}

	public void setErrorID(String errorID) {
		this.errorID = errorID;
	}

	@XmlElement(name = "message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlElement(name = "link")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@XmlElement(name = "http_status")
	public String getHttp_status() {
		return http_status;
	}

	public void setHttp_status(String http_status) {
		this.http_status = http_status;
	}

}
