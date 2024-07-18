package fr.enitodo.bll;

import java.time.LocalDate;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import fr.enitodo.bo.Tache;

import fr.enitodo.dal.TacheDAO;
import fr.enitodo.exceptions.BusinessCode;
import fr.enitodo.exceptions.BusinessException;


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
		// validation
		BusinessException be = new BusinessException();
		boolean isValid = true;
		isValid &= validerTitre(tache.getTitre(), be);
		System.out.println("IS VALID STATUT APRES TITRE = "+isValid);
		isValid &= validerPriorite(tache.getStatutDePriorite(), be);
		System.out.println("IS VALID STATUT APRES PRIO = "+isValid);
		isValid &= validerDate(tache.getDateDeFin(), be);
		System.out.println("IS VALID STATUT APRES DATE = "+isValid);
		System.out.println("ON PASSE DANS CREATE TACHE DANS LA BLL");
		if (isValid) {
			try {
				System.out.println("ON VALIDE LE FORMULAIRE DANS LA BLL");
				tacheDAO.createTache(tache);
			} catch (DataAccessException e) {
				System.out.println("ON CATCH UNE ERREUR DANS LA BLL");
				be.add(BusinessCode.BLL_TACHE_AJOUT_ERREUR);
				throw be;
			}
		} else {
			System.out.println("ISVALID N'EST PAS VALIDE");
			throw be;
		}

	}

	@Override
	public List<Tache> getTacheParProjet(long id) {
		return tacheDAO.findAllTaskProjet(id);
	}

	@Override
	public Tache findById(int id) {
		return tacheDAO.findById(id);
	}

	@Override
	public void archiverTache(int id) {
		tacheDAO.archiverTache(id);
	}

	@Override
	public void desarchiverTache(int id) {
		tacheDAO.desarchiverTache(id);
		
	}
	
	/**
	 * MÃ©thodes de validation des BO
	 */
	private boolean validerTitre(String titre, BusinessException be) {
		if (titre == null || titre.isBlank()) {
			be.add(BusinessCode.VALIDATION_TACHE_TITRE_BLANK);
			return false;
		}
		if (titre.length() < 2 || titre.length() > 250) {
			be.add(BusinessCode.VALIDATION_TACHE_TITRE_LENGTH);
			return false;
		}
		return true;
	}
	
	private boolean validerPriorite(int p, BusinessException be) {
		if (p == 0) {
			be.add(BusinessCode.VALIDATION_PRIORITE_BLANK);
			return false;
		}
		return true;
	}
	
	private boolean validerDate(LocalDate d, BusinessException be) {
		if (d == null) {
			be.add(BusinessCode.VALIDATION_DATEDEFIN_BLANK);
			return false;
		}
		return true;
	}
	
	
	

}