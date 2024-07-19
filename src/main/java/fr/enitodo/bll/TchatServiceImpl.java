package fr.enitodo.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import fr.enitodo.bo.Message;
import fr.enitodo.dal.TacheDAO;
import fr.enitodo.dal.TchatDAO;
import fr.enitodo.exceptions.BusinessCode;
import fr.enitodo.exceptions.BusinessException;

@Service
public class TchatServiceImpl implements TchatService {
	
	
	private TchatDAO tchatDAO;
	
	public TchatServiceImpl(TchatDAO tchatDAO) {
		
		this.tchatDAO = tchatDAO;
	
	}

	@Override
    public List<Message> getMessage() {
        return tchatDAO.findAll();
    }

    @Override
    public void createMessage(Message message) {
    	// validation
    			BusinessException be = new BusinessException();
    			boolean isValid = true;
    			isValid &= validerMessage(message.getMessage(), be);
    			System.out.println("IS VALID STATUT APRES TITRE = "+isValid);
    			if (isValid) {
    				try {
    					System.out.println("ON VALIDE LE FORMULAIRE DANS LA BLL");
    					tchatDAO.createMessage(message);
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
    public void addMessage(String message) {
        Message msg = new Message();
        msg.setMessage(message);
        tchatDAO.createMessage(msg);
    }

	@Override
	public Message read(String message) {
		return tchatDAO.read(message);
	}

	private boolean validerMessage(String message, BusinessException be) {
		if (message == null || message.isBlank()) {
			be.add(BusinessCode.VALIDATION_TACHE_TITRE_BLANK);
			return false;
		}
		if (message.length() < 1 || message.length() > 900) {
			be.add(BusinessCode.VALIDATION_TACHE_TITRE_LENGTH);
			return false;
		}
		return true;
	}

	@Override
	public List<Message> getMessageById(long id) {
		return tchatDAO.getMessageById(id);
	}

	@Override
	public List<Message> readAllMessage(String username) {
		return tchatDAO.findAll(username);
	}

	
	


}