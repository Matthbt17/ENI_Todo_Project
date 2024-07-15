package fr.enitodo.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Projet implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String titre;
	private String pseudo;
	private Tache tache;
	private long codeProjet;
	private List<Tache> listeTaches;

	public Projet(long id, String titre, String pseudo, Tache tache, long codeProjet) {
		super();
		this.id = id;
		this.titre = titre;
		this.pseudo = pseudo;
		this.tache = tache;
		this.codeProjet = codeProjet;
		this.listeTaches = new ArrayList<>();
		this.listeTaches.add(tache);
	}
	
	public Projet() {
		
	}
	
	public Projet(String titre, String pseudo, long codeProjet) {
		this.titre = titre;
		this.pseudo = pseudo;
		this.codeProjet = codeProjet;
		this.listeTaches = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	public long getcodeProjet() {
		return codeProjet;
	}

	public void setcodeProjet(long codeProjet) {
		this.codeProjet = codeProjet;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void ajouterTache(Tache tache) {
		this.listeTaches.add(tache);
	}
	
	public void deleteTache(Tache tache) {
		for(Tache t : this.listeTaches) {
			if (t.getId() == tache.getId()) {
				listeTaches.remove(t);
			}
		}
	}
	
	public void setTacheComplete(Tache tache) {
		for(Tache t : this.listeTaches) {
			if (t.getId() == tache.getId()) {
				t.setComplete(true);
			}
		}
	}
	
	public void updateTache(Tache tache) {
		for(Tache t : this.listeTaches) {
			if (t.getId() == tache.getId()) {
				listeTaches.set(((int)tache.getId()), tache);
			}
		}
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("Projet (");
		builder.append(id);
		builder.append(")\n\ttitre : ");
		builder.append(titre+"\n");
		builder.append("[pseudo : ");
		builder.append(pseudo+"\n");
		builder.append("[taches : ");
		builder.append(tache+"\n");
		builder.append(", code projet : ");
		builder.append(codeProjet+"\n"+"\n");
		return builder.toString();

	}
}
