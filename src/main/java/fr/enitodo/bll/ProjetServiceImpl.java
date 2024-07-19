package fr.enitodo.bll;

import java.util.List;

import org.springframework.dao.DataAccessException;
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
		validComplet &= validerTitre(projet.getTitre(), be);
		validComplet &= verifCodeProjet(projet.getCodeProjet(), be);
		if(validComplet) {
			try {
				projetDAO.create(projet);
			} catch(DataAccessException e) {
				be.add(BusinessCode.BLL_PROJET_AJOUT_ERREUR);
				throw be;
			}
			
		} else {
			throw be;
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
	public Projet readParCode(int code) {
		return projetDAO.recupParCodeProjet(code);
	}

	@Override
	public List<Projet> findAll() {
		return projetDAO.readAll();
	}
	
	@Override
	public List<Projet> readAllProjetMember(String username){
		return projetDAO.findAllProjectUser(username);
	}
	
	@Override
	public int readCode(int code) {
		return projetDAO.readParCodeProjet(code);
	}
	
	private boolean validerTitre(String titre, BusinessException be) {
		 be = new BusinessException();
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
	
	private boolean verifCodeProjet(long codeProjet,BusinessException be) {
		if(codeProjet == 0) {
			be.add(BusinessCode.VALIDATION_CODEPROJET_BLANK);
			return false;
		}
		if(codeProjet >= 100000 || codeProjet < 100) {
			be.add(BusinessCode.VALIDATION_CODEPROJET_LENGTH);
			return false;
		}
		if(projetDAO.readParCodeProjet(codeProjet) >= 1) {
			be.add(BusinessCode.VALIDATION_CODEPROJET_EXIST);
			return false;
		}
		return true;
	}

	

}
