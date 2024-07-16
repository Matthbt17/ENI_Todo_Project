package fr.enitodo.dal;



import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.enitodo.bo.Projet;
import fr.enitodo.bo.Tache;
import fr.enitodo.bo.Utilisateur;

@Repository
public class TacheDAOImpl implements TacheDAO {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public Tache read(String titre) {
		String sql = "SELECT id, titre, date_de_fin, statut_de_priorite, id_projet, description FROM TACHE WHERE titre =:titre";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("titre", titre);
		return jdbc.queryForObject(sql, params, new TacheRowMapper());
	}

	@Override
	public void createTache(Tache tache) {
		KeyHolder key = new GeneratedKeyHolder();
		String sql = "INSERT INTO TACHE(titre, date_de_fin, statut_de_priorite, id_projet, description) "
				+ "VALUES(:titre, :dateDeFin, :statutDePriorite, :idProjet, :description)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("titre", tache.getTitre());
		params.addValue("dateDeFin", Date.valueOf(tache.getDateDeFin()));
		params.addValue("statutDePriorite", tache.getStatutDePriorite());
		params.addValue("idProjet", tache.getIdProjet());
		params.addValue("description", tache.getDescription());
		jdbc.update(sql, params, key);
		if(key != null && key.getKey() != null) {
			tache.setId(key.getKey().intValue());
		}
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
		String sql = "SELECT id, titre, date_de_fin, statut_de_priorite, id_projet, description FROM TACHE";
		return jdbc.query(sql, new TacheRowMapper());
	}
	
	@Override
	public List<Tache> findAllTaskProjet(long id) {
		System.out.println("L'ID est de : "+id);
		String sql = "SELECT id, titre, date_de_fin, statut_de_priorite, id_projet, description FROM TACHE"
				+ " WHERE id_projet = :idProjet";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("idProjet", id);
		return jdbc.query(sql, params, new TacheRowMapper());
	}
	
	private static class TacheRowMapper implements RowMapper<Tache> {
        @Override
        public Tache mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Tache tache = new Tache();
        	tache.setId(rs.getInt("id"));
        	tache.setTitre(rs.getString("titre"));
        	Date date = rs.getDate("date_de_fin");
        	LocalDate localDate = date.toLocalDate();
        	tache.setDateDeFin(localDate);
        	tache.setStatutDePriorite(rs.getInt("statut_de_priorite"));
            tache.setIdProjet(rs.getInt("id_projet"));
            tache.setDescription(rs.getString("description"));
            return tache;
        }
    }

	

}