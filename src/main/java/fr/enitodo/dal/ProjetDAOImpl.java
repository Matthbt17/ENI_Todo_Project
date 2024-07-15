package fr.enitodo.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.enitodo.bo.Projet;

@Repository
public class ProjetDAOImpl implements ProjetDAO {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public void create(Projet projet) {
		KeyHolder key = new GeneratedKeyHolder();
		String sql = "INSERT INTO PROJET(titre, pseudo_membre, code_projet) VALUES (:titre, :pseudoMembre, :codeProjet)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("titre", projet.getTitre());
		params.addValue("pseudoMembre", projet.getPseudo());
		params.addValue("codeProjet", projet.getcodeProjet());
		jdbc.update(sql, params, key);

		if(key != null && key.getKey() != null) {
			System.out.println("On utilise la méthode pour set l'ID du projet");
			projet.setId(key.getKey().intValue());
			System.out.println("L'ID du projet créé sera : "+projet.getId());
		}
	}

	@Override
	public void delete(Projet projet) {
		String sql = "DELETE FROM PROJET WHERE titre=:titre";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("titre", projet.getTitre());
		jdbc.update(sql, params);
	}

	@Override
	public void update(Projet projet) {
		// TODO Auto-generated method stub
	}

	@Override
	public Projet read(String titreProjet) {
		String sql = "SELECT id, titre, pseudo_membre, id_tache, code_projet FROM PROJET WHERE titre=:titre";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("titre", titreProjet);
		return jdbc.queryForObject(sql, params, new BeanPropertyRowMapper<>(Projet.class));
	}

	@Override
	public Projet read(int id) {
		String sql = "SELECT id, titre, pseudo_membre, id_tache, code_projet FROM PROJET WHERE id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.queryForObject(sql, params, new BeanPropertyRowMapper<>(Projet.class));
	}

	@Override
	public List<Projet> readAll() {
		String sql = "SELECT id, titre, pseudo_membre, id_tache, code_projet FROM PROJET";
		return jdbc.query(sql, new BeanPropertyRowMapper<>(Projet.class));
	}
	
	@Override
	public List<Projet> findAllProjectUser(String username){
		String sql = "SELECT id, titre, pseudo_membre, code_projet FROM PROJET where pseudo_membre = :pseudoMembre";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("pseudoMembre", username);
        
		return jdbc.query(sql, params, new ProjetRowMapper());
	}
	
	private static class ProjetRowMapper implements RowMapper<Projet> {
        @Override
        public Projet mapRow(ResultSet rs, int rowNum) throws SQLException {
            Projet projet = new Projet();
            projet.setTitre(rs.getString("titre"));
            projet.setId(rs.getInt("id"));
            projet.setPseudo(rs.getString("pseudo_membre"));
            projet.setcodeProjet(rs.getLong("code_projet"));
            return projet;
        }
    }
}
