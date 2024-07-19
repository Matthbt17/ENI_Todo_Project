package fr.enitodo.dal;

import java.util.List;

import fr.enitodo.bo.Projet;

public interface ProjetDAO {
	void create(Projet projet);
	void delete(Projet projet);
	void update(Projet projet);
	Projet read(String titreProjet);
	Projet read(int id);
	int readParCodeProjet(long codeProjet);
	List<Projet> readAll();
	List<Projet> findAllProjectUser(String username);
	Projet recupParCodeProjet(int code);
}
