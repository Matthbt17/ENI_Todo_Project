package fr.enitodo.bo;

import java.io.Serializable;

public class Utilisateur implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pseudo;
	private String password;

	public Utilisateur(String pseudo, String password) {
		super();
		this.pseudo = pseudo;
		this.password = password;
	}
	
	public Utilisateur() {
		
	}
	

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getPseudo() {
		return pseudo;
	}



	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}



	@Override
	public String toString() {
		return "Utilisateur [pseudo=" + pseudo + "]";
	}
	
	

}
