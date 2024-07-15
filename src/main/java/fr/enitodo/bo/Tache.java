package fr.enitodo.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class Tache implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String titre;
	private LocalDate dateDeFin;
	private int statutDePriorite;
	private boolean isComplete;

	public Tache(long id, String titre, LocalDate dateDeFin, int statutDePriorite, boolean isComplete) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateDeFin = dateDeFin;
		this.statutDePriorite = statutDePriorite;
		this.isComplete = isComplete;
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
		builder.append("titre : ");
		builder.append(titre);
		builder.append("Dead Line :");
		builder.append(dateDeFin);
		builder.append(", PrioritÃ©s : ");
		builder.append(statutDePriorite);
		return builder.toString();

	}

}