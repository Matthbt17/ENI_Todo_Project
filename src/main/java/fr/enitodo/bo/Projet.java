package fr.enitodo.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Projet implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	
	@NotBlank
	private String titre;
	
	private String pseudo;
	private Tache tache;
	
	@NotBlank
	@Size(min = 100, max= 99999, message = "Le titre doit avoir au moins 4 caractères")
	private long codeProjet;
	
	private List<Tache> listeTaches;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Projet(long id, String titre, String pseudo, Tache tache, long codeProjet,String description) {
		super();
		this.id = id;
		this.titre = titre;
		this.pseudo = pseudo;
		this.tache = tache;
		this.codeProjet = codeProjet;
		this.listeTaches = new ArrayList<>();
		this.listeTaches.add(tache);
		this.description = description;
	}
	
	public Projet() {
		
	}
	
	public Projet(String titre, String pseudo, long codeProjet, String description) {
		this.titre = titre;
		this.pseudo = pseudo;
		this.codeProjet = codeProjet;
		this.listeTaches = new ArrayList<>();
		this.description = description;
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

	public long getCodeProjet() {
		return codeProjet;
	}

	public void setCodeProjet(long codeProjet) {
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
		builder.append(codeProjet+"\n");
		builder.append(", description : ");
		builder.append(description+"\n"+"\n");
		return builder.toString();

	}
}
