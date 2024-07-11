package fr.enitodo.dal;

import fr.enitodo.bo.Membre;

public interface MembreDAO {
	void create(Membre membre);
	void update(Membre membre);
	Membre read(long id);
	Membre read(String email);
	void delete(Membre membre);
}
