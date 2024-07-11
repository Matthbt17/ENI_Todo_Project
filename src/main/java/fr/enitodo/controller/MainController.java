package fr.enitodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.enitodo.bo.Membre;
import fr.enitodo.bo.Utilisateur;
import fr.enitodo.dal.MembreDAO;
import fr.enitodo.dal.UtilisateurDAO;

@Controller
public class MainController {
	
	//A remplacer par les interfaces de BLL (aka SERVICE)
	private MembreDAO membreDAO;
	private UtilisateurDAO utilisateurDAO;

	public MainController(MembreDAO membreDAO, UtilisateurDAO utilisateurDAO) {
		this.membreDAO = membreDAO;
		this.utilisateurDAO = utilisateurDAO;
	}

	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("/aboutus")
	public String aboutus() {
		return "view-aboutus";
	}
	
	@GetMapping("/signin")
	public String signIn() {
		return "view-signin";
	}
	
	@PostMapping("/signin")
	public String signInF(
			@RequestParam(name="username", required=true) String username,
			@RequestParam(name="password", required=true) String pw
			){
		System.out.println("Le username est : "+username);
		System.out.println("Le pw est : "+pw);
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		/*
		 * TODO :
		 *      Il faut faire appel à UtilisateurServiceImpl ici.
		 *      La version là est dégueu (mais fonctionnelle mdr)
		 *
		 */
		Utilisateur result = new Utilisateur(username, encoder.encode(pw));
		utilisateurDAO.createUser(result);
		utilisateurDAO.addRole(result);
		return "redirect:/";
	}
	

}
