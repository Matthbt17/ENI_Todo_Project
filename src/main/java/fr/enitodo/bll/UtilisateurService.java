package fr.enitodo.bll;

import java.util.List;

import fr.enitodo.bo.Utilisateur;

public interface UtilisateurService {
	void ajouterUtilisateur(Utilisateur utilisateur);
	List<Utilisateur> getAllUsers();
	Utilisateur findByUsername(String username);

	void update(Utilisateur utilisateur, String newPseudo);
	void delete(Utilisateur utilisateur);
}
