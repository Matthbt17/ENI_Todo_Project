package fr.enitodo.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.enitodo.bo.Projet;
import fr.enitodo.dal.ProjetDAO;

@Service
public class ProjetServiceImpl implements ProjetService{
	
	private ProjetDAO projetDAO;
	
	public ProjetServiceImpl(ProjetDAO projetDAO) {
		this.projetDAO = projetDAO;
	}

	@Override
	public void creerProjet(Projet projet) {
		projetDAO.create(projet);
		
	}

	@Override
	public void deleteProjet(Projet projet) {
		projetDAO.delete(projet);
		
	}

	@Override
	public void updateProjet(Projet projet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Projet read(String titreProjet) {
		return projetDAO.read(titreProjet);
	}

	@Override
	public Projet read(int id) {
		return projetDAO.read(id);
	}

	@Override
	public List<Projet> findAll() {
		return projetDAO.readAll();
	}
	
	@Override
	public List<Projet> readAllProjetMember(String username){
		return projetDAO.findAllProjectUser(username);
	}

}
