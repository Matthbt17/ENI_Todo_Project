package fr.enitodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.enitodo.bll.TacheService;
import fr.enitodo.bo.Tache;

@Controller
@RequestMapping("/projet")
public class TacheController {

    @Autowired
    private TacheService tacheService;

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
}