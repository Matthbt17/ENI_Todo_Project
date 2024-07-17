package fr.enitodo.dal;

import java.util.List;

import fr.enitodo.bo.Tache;


public interface TacheDAO {
Tache read(String titre);
void createTache(Tache tache);
void delete(Tache tache);
List<Tache> findAll();
List<Tache> findAllTaskProjet(long id);
Tache findById(int id);
void archiverTache(int id);
void desarchiverTache(int id);
}