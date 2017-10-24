package com.EM2.in28MinutesCourseApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.EM2.in28MinutesCourseApp.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {
	
//	@RequestMapping("/login")
//	public String loginMessage(@RequestParam String name, ModelMap model) {
//		model.put("name", name);
//		return "login";
//	}
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam String name, @RequestParam String password, ModelMap model) {
		if(loginService.validateUser(name, password)) {
			model.put("name", name);
			model.put("password", password);
			return "welcome";
		}
		model.put("errorMessage", "Username/password don't match");
		return "login";
	}
	
}
