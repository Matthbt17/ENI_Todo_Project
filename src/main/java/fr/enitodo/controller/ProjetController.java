package fr.enitodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projet")
public class ProjetController {

	@GetMapping
	public String projetMain() {
		return "view-projet";
	}
}
