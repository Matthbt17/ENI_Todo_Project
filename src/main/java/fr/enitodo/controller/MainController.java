package fr.enitodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.enitodo.bll.TchatService;
import fr.enitodo.bll.UtilisateurService;
import fr.enitodo.bo.Membre;
import fr.enitodo.bo.Message;
import fr.enitodo.bo.Projet;
import fr.enitodo.bo.Tache;
import fr.enitodo.bo.Utilisateur;
import fr.enitodo.dal.MembreDAO;
import fr.enitodo.dal.UtilisateurDAO;
import fr.enitodo.exceptions.BusinessException;
import jakarta.validation.Valid;

@Controller
public class MainController {
	
	@Autowired
	TchatService tchatService;


	UtilisateurService userService;
	
	public MainController(UtilisateurService userService, TchatService tchatService) {
		this.userService = userService;
		this.tchatService = tchatService;
	}

	public MainController(UtilisateurService userService) {
		super();
		this.userService = userService;
	}
	
	public MainController(TchatService tchatService) {
		super();
		this.tchatService = tchatService;
	}
	
	public MainController() {
		
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
	public String signIn(Model model) {
		Utilisateur u = new Utilisateur();
		model.addAttribute("user",u);
		return "view-signin";
	}

	@PostMapping("/signin")
	public String signInF(@ModelAttribute("user") Utilisateur user, BindingResult bindingResult) {
		System.out.println(user.getPseudo() + ' ' + user.getPassword());
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		if (bindingResult.hasErrors()) {
			return "view-signin";
		} else {
			try {
				userService.ajouterUtilisateur(user);
				return "redirect:/";
			} catch(BusinessException e){
				e.getClefsExternalisations().forEach(key -> {
					ObjectError error = new ObjectError("globalError", key);
					bindingResult.addError(error);
				});
				return "view-signin";
			}
		}

	}
	
	

		@GetMapping("/tchat")
		public String getMessage(Model model) {
		    List<Message> lstMessage = tchatService.getMessage();
		    model.addAttribute("messages", lstMessage);
		    model.addAttribute("tchat", new Message());
		    return "view-tchat";
		}

		@PostMapping("/tchat")
		String creaMessage(
				  @ModelAttribute("message") Message message, BindingResult result, 
				  @CurrentSecurityContext(expression="authentication?.name") String userLogged,Model model) {
		    if (result.hasErrors()) {
		        model.addAttribute("messages", tchatService.getMessage());
		        return "view-tchat";
		    }
		    message.setPseudo(userLogged);
		    tchatService.createMessage(message);
		    return "redirect:/tchat";
		}
	}
