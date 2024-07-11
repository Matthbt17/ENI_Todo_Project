package fr.enitodo.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import fr.enitodo.bo.Membre;

@Repository
public class MembreDAOImpl implements MembreDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@Override
	public void update(Membre membre) {
		// TODO Auto-generated method stub
	}

	@Override
	public Membre read(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Membre read(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Membre membre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(Membre membre) {
		// TODO Auto-generated method stub
		
	}

	

}
