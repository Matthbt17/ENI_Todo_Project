package fr.enitodo.bll;

import java.util.List;

import fr.enitodo.bo.Tache;

public interface TacheService {
	
	List<Tache> getTache();
	
	Tache findByTitre (String titreTache);
	
	Tache findById (int id);

	void createTache(Tache tache);
	
	List<Tache> getTacheParProjet(long id);
	
	void archiverTache(int id);
	
	void desarchiverTache(int id);
}