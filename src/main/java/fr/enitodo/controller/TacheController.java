package fr.enitodo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.enitodo.bll.ProjetService;
import fr.enitodo.bll.TacheService;
import fr.enitodo.bo.Projet;
import fr.enitodo.bo.Tache;
import fr.enitodo.bo.Utilisateur;

@Controller
@RequestMapping("/projet")
@SessionAttributes({ "projetsEnSession", "MembreEnSession" })
public class TacheController {

    private TacheService tacheService;
    private ProjetService projetService;

    public TacheController(TacheService tacheService, ProjetService projetService) {
		this.tacheService = tacheService;
		this.projetService = projetService;
	}

    @ModelAttribute("projetsEnSession")
    public List<Projet> chargerProjetUtilisateur(){
    		List<Projet> listeProjet = new ArrayList<>();
    	return listeProjet;
    }
    
    @ModelAttribute("membreEnSession")
    public Utilisateur chargerUtilisateur() {
    	Utilisateur test = new Utilisateur();
    	System.out.println("Chargement de l'utilisateur par défaut");
    	return test;
    }
    
	@GetMapping("/taches")
    public String getTaches(Model model) {
        List<Tache> lstTaches = tacheService.getTache();
        model.addAttribute("taches", lstTaches);
        return "view-projet";
    }
    
    @PostMapping("/taches/add")
    public String addTache(@ModelAttribute Tache tache) {
        tacheService.createTache(tache);
        return "redirect:/projet/taches";
    }
    
    @GetMapping
    String viewFormCrea(@ModelAttribute("projet") Projet projet,
    					@ModelAttribute("projetsEnSession") List<Projet> lstProjets, 
    					@CurrentSecurityContext(expression="authentication?.name") String userLogged,
    					Model model) {
    	model.addAttribute("projet", new Projet());
    	System.out.println("User logged : "+userLogged);
    	lstProjets = projetService.readAllProjetMember(userLogged);
    	System.out.println(lstProjets);
    	return "view-projet";
    }
    
    @PostMapping
    String creaProjet(@CurrentSecurityContext(expression="authentication?.name")
    				  String userLogged,
    				  @ModelAttribute("projet") Projet projet) {
    	projet.setPseudo(userLogged);
    	System.out.println(projet);
    	projetService.creerProjet(projet);
    	System.out.println("PSEUDO DU PROJET = "+projet.getPseudo());
    	return "redirect:/";
    }
}