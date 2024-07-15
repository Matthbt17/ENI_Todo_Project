package fr.enitodo.dal;

import java.util.List;

import fr.enitodo.bo.Tache;


public interface TacheDAO {
	Tache read(String titre);
	void createTache(Tache tache);
	void delete(Tache tache);
	List<Tache> findAll();
	
	
	

}