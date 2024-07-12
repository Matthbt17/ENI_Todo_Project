package fr.enitodo.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.enitodo.bo.Utilisateur;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public void createUser(Utilisateur utilisateur) {
		String sql = "INSERT INTO UTILISATEUR(pseudo, password) VALUES(:pseudo, :password)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("pseudo", utilisateur.getPseudo());
		params.addValue("password", utilisateur.getPassword());
		jdbc.update(sql, params);
	}

	@Override
	public void addRole(Utilisateur utilisateur) {
		String sql = "INSERT INTO ROLES(pseudo, role) VALUES (:pseudo, 'USER_MEMBER')";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("pseudo", utilisateur.getPseudo());
		jdbc.update(sql, params);
	}

	@Override
	public void update(Utilisateur utilisateur, String newPseudo) {
		String sql = "UPDATE UTILISATEUR SET pseudo= :newPseudo WHERE pseudo= :pseudo";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("newPseudo", newPseudo);
		params.addValue("pseudo", utilisateur.getPseudo());
		jdbc.update(sql, params);
	}

	@Override
	public Utilisateur read(String pseudo) {
		String sql = "SELECT pseudo FROM UTILISATEUR WHERE pseudo = :pseudo";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("pseudo", pseudo);
		return jdbc.queryForObject(sql, params, new BeanPropertyRowMapper<>(Utilisateur.class));
	}

	@Override
	public List<Utilisateur> findAll() {
		String sql = "SELECT pseudo FROM UTILISATEUR";
		return jdbc.query(sql, new BeanPropertyRowMapper<>(Utilisateur.class));
	}

	@Override
	public void delete(Utilisateur utilisateur) {
		String sql = "DELETE FROM UTILISATEUR WHERE pseudo = :pseudo";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("pseudo", utilisateur.getPseudo());
		jdbc.update(sql, params);
	}
}
