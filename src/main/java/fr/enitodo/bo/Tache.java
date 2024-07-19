package fr.enitodo.bo;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Tache implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	@NotBlank
	@Size(min = 2, max=250, message = "Le titre doit avoir au moins 4 caractères")
	private String titre;
	
	@NotNull
	private LocalDate dateDeFin;
	
	@NotNull
	private int statutDePriorite;
	
	private boolean isComplete;
	private String description;
	private int idProjet;

	public Tache(long id, String titre, LocalDate dateDeFin, int statutDePriorite, boolean isComplete, String description, int idProjet) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateDeFin = dateDeFin;
		this.statutDePriorite = statutDePriorite;
		this.isComplete = isComplete;
		this.description = description;
		this.idProjet = idProjet;
	}
	
	

	public Tache() {
		
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

	public LocalDate getDateDeFin() {
		return dateDeFin;
	}

	public void setDateDeFin(LocalDate dateDeFin) {
		this.dateDeFin = dateDeFin;
	}

	public int getStatutDePriorite() {
		return statutDePriorite;
	}

	public void setStatutDePriorite(int statutDePriorite) {
		this.statutDePriorite = statutDePriorite;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id : ");
		builder.append(id+"\n");
		builder.append("titre : ");
		builder.append(titre+"\n");
		builder.append("isComplete : ");
		builder.append(isComplete+"\n");
		builder.append("description : ");
		builder.append(description+"\n");
		builder.append("id PROJET : ");
		builder.append(idProjet+"\n");
		builder.append("Dead Line :");
		builder.append(dateDeFin+"\n");
		builder.append(", Priorités : ");
		builder.append(statutDePriorite+"\n");
		return builder.toString();

	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getIdProjet() {
		return idProjet;
	}



	public void setIdProjet(int idProjet) {
		this.idProjet = idProjet;
	}

}