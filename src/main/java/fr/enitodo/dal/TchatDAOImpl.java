package fr.enitodo.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.RowMapper;

import fr.enitodo.bo.Message;


@Repository
public class TchatDAOImpl implements TchatDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public Message read(String message) {
	    String sql = "SELECT id, message, pseudo FROM TCHAT WHERE message = :message";
	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("message", message);
	    
	   
	        return jdbc.queryForObject(sql, params, new TchatRowMapper());
	    
	}


	@Override
	public void createMessage(Message message) {
		KeyHolder key = new GeneratedKeyHolder();
		String sql = "INSERT INTO TCHAT (message, pseudo) VALUES (:message, :pseudo)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("message", message.getMessage());
		params.addValue("pseudo", message.getPseudo());
		jdbc.update(sql, params, key);
		if(key != null && key.getKey() != null) {
			message.setId(key.getKey().intValue());
		}
	}

	@Override
	public List<Message> findAll() {
		String sql = "SELECT id, message, pseudo FROM TCHAT ORDER BY id DESC";
		return jdbc.query(sql, new TchatRowMapper());
	}

	private static class TchatRowMapper implements RowMapper<Message> {
		@Override
		public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
			Message message = new Message();
			message.setId(rs.getInt("id"));
			message.setMessage(rs.getString("message"));
			message.setPseudo(rs.getString("pseudo"));
			return message;
		}
	}

	@Override
	public List<Message> getMessageById(long id) {
		String sql = "SELECT id, message FROM TCHAT "
				+ " WHERE id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.query(sql, params, new TchatRowMapper());
	}


	@Override
	public Object createMessage(String message) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Message> findAll(String username) {
		String sql = "SELECT id, message, pseudo FROM TCHAT";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("pseudo", username);
		return jdbc.query(sql, new TchatRowMapper());
	}

	
}