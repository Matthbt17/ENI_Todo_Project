package fr.enitodo.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.enitodo.bo.Tache;
import fr.enitodo.bo.Utilisateur;

@Repository
public class TacheDAOImpl implements TacheDAO {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public Tache read(String titre) {
		String sql = "SELECT titre FROM TACHE WHERE titre =:titre";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("Titre", titre);
		return jdbc.queryForObject(sql, params, new BeanPropertyRowMapper<>(Tache.class));
	}

	@Override
	public void createTache(Tache tache) {
		String sql = "INSERT INTO TACHE(titre, statutDePriorite, dateDeFin) VALUES(:titre, :statutDePriorite, :dateDeFin)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("titre", tache.getTitre());
		params.addValue("statutDePriorite", tache.getStatutDePriorite());
		params.addValue("dateDeFin", tache.getDateDeFin());
		jdbc.update(sql, params);
		
	}

	@Override
	public void delete(Tache tache) {
		String sql = "DELETE FROM TACHE WHERE titre = :titre";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("titre", tache.getTitre());
		jdbc.update(sql, params);
	}

	@Override
	public List<Tache> findAll() {
		String sql = "SELECT titre FROM TACHE";
		return jdbc.query(sql, new BeanPropertyRowMapper<>(Tache.class));
	}

}