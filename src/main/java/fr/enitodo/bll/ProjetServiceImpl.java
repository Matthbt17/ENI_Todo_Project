package fr.enitodo.bll;

import java.util.List;

import org.springframework.stereotype.Service;


import fr.enitodo.exceptions.BusinessCode;
import fr.enitodo.exceptions.BusinessException;

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
		BusinessException be = new BusinessException();
		boolean validComplet = true;
		validComplet &= validerTitre(projet.getTitre());
		validComplet &= verifCodeProjet(projet.getcodeProjet());
		if(validComplet) {
			projetDAO.create(projet);
		}
		
		
	}

	@Override
	public void deleteProjet(Projet projet) {
		projetDAO.delete(projet);
		
	}

	@Override
	public void updateProjet(Projet projet) {
		
		
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
	
	/**
	 * MÃ©thodes de validation des BO
	 */
	private boolean validerTitre(String titre) {
		BusinessException be = new BusinessException();
		if (titre == null || titre.isBlank()) {
			be.add(BusinessCode.VALIDATION_PROJET_TITRE_BLANK);
			return false;
		}
		if (titre.length() < 2 || titre.length() > 250) {
			be.add(BusinessCode.VALIDATION_PROJET_TITRE_LENGTH);
			return false;
		}
		return true;
	}
	
	private boolean verifCodeProjet(long codeProjet) {
		BusinessException be = new BusinessException();
		if(codeProjet == 0) {
			be.add(BusinessCode.VALIDATION_CODEPROJET_BLANK);
			return false;
		}
		if(codeProjet >= 100000 || codeProjet < 100) {
			be.add(BusinessCode.VALIDATION_CODEPROJET_LENGTH);
			return false;
		}
		if(projetDAO.readParCodeProjet(codeProjet) != null) {
			be.add(BusinessCode.VALIDATION_CODEPROJET_EXIST);
			return false;
		}
		return true;
	}

}
