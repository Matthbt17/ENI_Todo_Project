package fr.enitodo.dal;

import java.util.List;

import fr.enitodo.bo.Message;
import fr.enitodo.bo.Projet;


public interface TchatDAO {
	Message read(String message);
	void createMessage(Message message);
	List<Message> findAll();
	List<Message> getMessageById(long id);
	Object createMessage(String message);
	List<Message> findAll(String username);
	
	

}