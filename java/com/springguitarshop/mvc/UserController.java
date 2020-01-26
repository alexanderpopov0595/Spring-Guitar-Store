package com.springguitarshop.mvc;

import java.io.BufferedOutputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springguitarshop.domain.Guitar;
import com.springguitarshop.domain.User;
import com.springguitarshop.exception.ImageUploadException;
import com.springguitarshop.service.GuitarShopService;

//takes request ending with /user
@Controller
@RequestMapping("/users")
public class UserController {

	private GuitarShopService guitarShopService;

	@Autowired
	public UserController(GuitarShopService guitarShopService) {
		this.guitarShopService = guitarShopService;
	}

	@RequestMapping(value = "/{userlogin}/guitars", method = RequestMethod.GET)
	// write url part "userlogin" to the var
	public String listGuitarsForUsers(@PathVariable("userlogin") String login, Model model) {
		User user = guitarShopService.getUser(login);
		List<Guitar> guitarsList = guitarShopService.getAllGuitarsForUser(user);
		model.addAttribute(user);
		model.addAttribute(guitarsList);
		// return a path to jsp (guitars is a folder)
		return "guitars/list";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute(new User());
		return "users/form";
	}

	// this method called when url is /users/registration and method is post (jsp
	// has a form with POST method)
	// in method params user must be validated
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String addUserFromForm(@Valid User user, BindingResult bindingResult,
			@RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest servletRequest) {
		// if there are some mistakes, bindingResult has errors
		if (bindingResult.hasErrors()) {
			// get back to edit page (back)
			return "users/form";
		}
		guitarShopService.addUser(user);
		// check if there is an image
		try {
			if (!image.isEmpty()) {
				// check if image is jpeg
				validateImage(image);
				// save image and make full name
				
				saveImage("users" + File.separator + user.getLogin() + ".jpg", image);
			}
		} catch (ImageUploadException e) {
		}
		// build url from root
		return "redirect:/users/" + user.getLogin();
	}

	private void validateImage(MultipartFile image) {
		if (!image.getContentType().equals("image/jpeg")) {
			throw new ImageUploadException("Only JPG images accepted");
		}
	}

	public void saveImage(String name, MultipartFile image) {

		try {
			// get root of apache server
			String rootPath = System.getProperty("catalina.home");
			// get path to image storing folder
			File dir = new File(rootPath + File.separator + "images" + File.separator);
			// create folder if there is none
			if (!dir.exists()) {
				// create folder
				dir.mkdirs();
			}
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(dir + File.separator + name));
			stream.write(image.getBytes());
			stream.close();
		} catch (IOException e) {
			throw new ImageUploadException("Unable to save image");
		}
	}

	// custom url which has path like /users/myname
	@RequestMapping(value = "/{userlogin}")
	public String showUser(@PathVariable String userlogin, Model model) {
		User user = guitarShopService.getUser(userlogin);
	
		model.addAttribute(user);
		return "users/view";
	}

	// edit user's profile
	@RequestMapping(value = "/{login}/edit", method = RequestMethod.GET)
	public String editUser(@PathVariable String login, Model model) {
	
		User user = guitarShopService.getUser(login);
		model.addAttribute(user);
		return "users/edit";
	}

	@RequestMapping(value = "/{login}/edit", method = RequestMethod.POST)
	public String editUserFromForm(@Valid User user, BindingResult bindingResult,
			@RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest servletRequest) {
		// if there are some mistakes, bindingResult has errors
		if (bindingResult.hasErrors()) {
			// get back to edit page (back)
			return "users/edit";
		}
		
		guitarShopService.saveUser(user);
		User changedUser=guitarShopService.getUser(user.getId());
		
		// check if there is an image
		try {
			if (!image.isEmpty()) {
				// check if image is jpeg
				validateImage(image);
				// save image and make full name
				saveImage(user.getLogin() + ".jpg", image);
			}
		} catch (ImageUploadException e) {
		}
		// build url from root
		return "redirect:/users/" + user.getLogin();
	}
	@RequestMapping(value="/{login}/delete/{id}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable long id, Model model) {		
		guitarShopService.deleteUser(id);
		deleteImage(id+".jpg");
		return "redirect:/static/j_spring_security_logout";
	}
	public void deleteImage(String fileName) {
		String rootPath=System.getProperty("catalina.home");
		String dir=rootPath + File.separator + "images" + File.separator+"users";
		File file=new File(dir+File.separator+fileName);
		file.delete();
	}
}
