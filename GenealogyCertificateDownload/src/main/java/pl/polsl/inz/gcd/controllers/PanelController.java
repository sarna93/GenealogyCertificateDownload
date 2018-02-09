package pl.polsl.inz.gcd.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panel")
public class PanelController {

	@RequestMapping("/familysearch")
	public String showFamilySearchPanel(Map<String, Object> model) {
		model.put("portal", "Family Search Panel");
		model.put("message", "You must be logged in familysearch.org");
		return "familySearchPanel";
	}

	@RequestMapping("/szukajwarchiwach")
	public String showSzukajWArchiwachPanel(Map<String, Object> model) {
		model.put("portal", "Szukaj w archiwach Panel");
		model.put("message", "");
		return "szukajWArchiwachPanel";
	}
}
