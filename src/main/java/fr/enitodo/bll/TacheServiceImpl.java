package fr.enitodo.bll;

import java.util.List;


import org.springframework.stereotype.Service;


import fr.enitodo.bo.Tache;

import fr.enitodo.dal.TacheDAO;


@Service
public class TacheServiceImpl implements TacheService {
	
	private TacheDAO tacheDAO;

	public TacheServiceImpl(TacheDAO tacheDAO) {
		
		this.tacheDAO = tacheDAO;
	
	}
	
	@Override
	public List<Tache> getTache() {
		return tacheDAO.findAll();
	}

	
	@Override
	public Tache findByTitre(String titreTache) {
		return tacheDAO.read(titreTache);
	}

	@Override
	public void createTache(Tache tache) {
		tacheDAO.createTache(tache);
	}

	@Override
	public List<Tache> getTacheParProjet(long id) {
		return tacheDAO.findAllTaskProjet(id);
	}

}