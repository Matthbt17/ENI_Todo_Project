package fr.enitodo.bll;

import java.util.List;

import fr.enitodo.bo.Message;
import fr.enitodo.bo.Projet;



public interface TchatService {
	
	
	List<Message> getMessage();
	
	List<Message> getMessageById(long id);
	
	void createMessage(Message message);
	
	void addMessage(String message);

	List<Message> readAllMessage(String username);

	Message read(String message);



}