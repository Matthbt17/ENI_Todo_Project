package fr.enitodo.dal;

import java.util.List;

import fr.enitodo.bo.Utilisateur;

public interface UtilisateurDAO {
	void addRole(Utilisateur utilisateur);
	void createUser(Utilisateur utilisateur);
	void update(Utilisateur utilisateur, String newPseudo);
	Utilisateur read(String pseudo);
	List<Utilisateur> findAll();
	void delete(Utilisateur utilisateur);
}
