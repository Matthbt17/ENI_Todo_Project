package fr.enitodo.bll;

import java.util.List;

import fr.enitodo.bo.Tache;

public interface TacheService {
	
	List<Tache> getTache();
	
	Tache findByTitre (String titreTache);

	void createTache(Tache tache);
	
	List<Tache> getTacheParProjet(long id);

}