package fr.enitodo.bll;

import java.util.List;

import fr.enitodo.bo.Utilisateur;
import fr.enitodo.dal.MembreDAO;

public class UtilisateurServiceImpl implements UtilisateurService {
	
	private MembreDAO membreDAO;

	public UtilisateurServiceImpl(MembreDAO membreDAO) {
		super();
		this.membreDAO = membreDAO;
	}

	@Override
	public void ajouterUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Utilisateur> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

}
