package com.springguitarshop.mvc;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springguitarshop.domain.User;
import com.springguitarshop.service.GuitarShopService;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String showLoginForm() {
		return "login";
	}

	private GuitarShopService guitarShopService;

	// inject guitarShopService from xml-file while creating a HomeController
	@Autowired
	public LoginController(GuitarShopService guitarShopService) {
		this.guitarShopService = guitarShopService;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String showProfile(Principal principal, Model model) {
		String login = principal.getName();

		return "redirect:/users/" + login;
	}

}
