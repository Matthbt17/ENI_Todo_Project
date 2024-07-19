package fr.enitodo.bll;

import java.util.List;

import fr.enitodo.bo.Projet;

public interface ProjetService {
	void creerProjet(Projet projet); 
	void deleteProjet(Projet projet);
	void updateProjet(Projet projet);
	Projet read(String titreProjet);
	Projet read(int id);
	int readCode(int code);
	Projet readParCode(int code);
	List<Projet> findAll();
	List<Projet> readAllProjetMember(String username);
}
