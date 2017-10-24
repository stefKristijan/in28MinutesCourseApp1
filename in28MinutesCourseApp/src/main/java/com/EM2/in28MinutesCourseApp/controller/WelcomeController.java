package com.EM2.in28MinutesCourseApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.EM2.in28MinutesCourseApp.service.LoginService;
//@sessionAttributes anotacija koja u atribut sesije name sprema vrijednost parametra name iz metode login()
//@SessionAttributes("name") ne treba zbog spring security-ja
@Controller
public class WelcomeController {
	
//	@RequestMapping("/login")
//	public String loginMessage(@RequestParam String name, ModelMap model) {
//		model.put("name", name);
//		return "login";
//	}
	
//	@Autowired
//	LoginService loginService;
//	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showLogin(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.put("name", authentication.getName());
		return "welcome";
	}
	
	
	/*u metodi login() se u parametar name sprema vrijednost fielda koji ima atribut name="name",
	 * a u parametar password se sprema vrijednost fielda koji ima atribut name="password"
	 * znači nije u @RequestParam potrebno dodavati parametre poput @RequestParam(value="name")
	 * nego je dovoljno da parametar u view-u ima isto ime kao parametar u metodi
	 * 
	 * objekt model klase ModelMap se mapira za view i nakon toga se u njega pomoću put() metode
	 * mogu dodavati razni objekti koji se nalaze u view-u
	 */
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String login(@RequestParam String name, @RequestParam String password, ModelMap model) {
//		if(loginService.validateUser(name, password)) {
//			model.put("name", name);
//			model.put("password", password);
//			return "welcome";
//		}
//		model.put("errorMessage", "Username/password don't match");
//		return "login";
//	}
	
}
