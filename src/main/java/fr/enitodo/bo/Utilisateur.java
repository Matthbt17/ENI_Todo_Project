package fr.enitodo.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pseudo;
	private String password;
	private List<Projet> listeProjets;

	public Utilisateur(String pseudo, String password) {
		super();
		this.pseudo = pseudo;
		this.password = password;
		this.listeProjets = new ArrayList<>();
	}
	
	public List<Projet> getListeProjets() {
		return listeProjets;
	}

	public void setListeProjets(List<Projet> listeProjets) {
		this.listeProjets = listeProjets;
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
