package fr.enitodo.bo;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	@NotBlank
	private String message;
	
	private String pseudo;
	
	public Message(long id, String message, String pseudo) {
		super();
		this.id = id;
		this.message = message;
		this.pseudo = pseudo;
	}

	public Message() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + "]";
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	
	
	

}