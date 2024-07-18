package fr.enitodo.bll;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import fr.enitodo.bo.Utilisateur;
import fr.enitodo.dal.MembreDAO;
import fr.enitodo.dal.UtilisateurDAO;
import fr.enitodo.exceptions.BusinessCode;
import fr.enitodo.exceptions.BusinessException;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurDAO utilisateurDAO;

	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
		super();
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public void ajouterUtilisateur(Utilisateur utilisateur) {
		BusinessException be = new BusinessException();
		List<Utilisateur> verifPseudo = utilisateurDAO.findAll();
		try {
			for (Utilisateur u : verifPseudo) {
				if (u.getPseudo().equals(utilisateur.getPseudo())) {
					be.add(BusinessCode.VALIDATION_PSEUDO_EXIST);
					throw be;
				}
			}
			utilisateurDAO.createUser(utilisateur);
			utilisateurDAO.addRole(utilisateur);
		} catch (DataAccessException e) {
			be.add(BusinessCode.VALIDATION_PSEUDO_EXIST);
			throw be;
		}
	}

	@Override
	public List<Utilisateur> getAllUsers() {
		return utilisateurDAO.findAll();
	}

	@Override
	public Utilisateur findByUsername(String username) {
		BusinessException be = new BusinessException();
		List<Utilisateur> verifPseudo = utilisateurDAO.findAll();
		Utilisateur result = new Utilisateur();
		try {
			result = utilisateurDAO.read(username);
			if (result.getPseudo() == null) {
				be.add(BusinessCode.VALIDATION_PSEUDO_EXIST);
				return null;
			}
		} catch (DataAccessException e) {
			be.add(BusinessCode.VALIDATION_PSEUDO_EXIST);
			return null;
		}
		return result;
	}

	@Override
	public void delete(Utilisateur utilisateur) {
		BusinessException be = new BusinessException();
		List<Utilisateur> verifPseudo = utilisateurDAO.findAll();
		Utilisateur result = new Utilisateur();
		try {
			for (Utilisateur u : verifPseudo) {
				if (u.getPseudo() == utilisateur.getPseudo()) {
					result = u;
				}
			}
			if (result.getPseudo() == null) {
				be.add(BusinessCode.VALIDATION_PSEUDO_EXIST);
			}
			utilisateurDAO.delete(result);
		} catch (DataAccessException e) {
			be.add(BusinessCode.VALIDATION_PSEUDO_EXIST);
		}

	}

	@Override
	public void update(Utilisateur utilisateur, String newPseudo) {
		BusinessException be = new BusinessException();
		List<Utilisateur> verifPseudo = utilisateurDAO.findAll();
		Utilisateur result = new Utilisateur();
		try {
			if (newPseudo.length() < 5) {
				System.err.println("pseudo trop court >:(");
			}
			for (Utilisateur u : verifPseudo) {
				if (u.getPseudo() == utilisateur.getPseudo()) {
					result = u;
				}
			}
			if (result.getPseudo() == null) {
				be.add(BusinessCode.VALIDATION_PSEUDO_EXIST);
			}
			utilisateurDAO.update(result, newPseudo);
		} catch (DataAccessException e) {
			be.add(BusinessCode.VALIDATION_PSEUDO_EXIST);
		}
	}
	
	/**
	 * MÃ©thode de validation
	 */
	

}