package com.springguitarshop.mvc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springguitarshop.domain.Guitar;
import com.springguitarshop.domain.User;
import com.springguitarshop.exception.ImageUploadException;
import com.springguitarshop.service.GuitarShopService;

@Controller
@RequestMapping("/guitars")
public class GuitarController {

	private GuitarShopService guitarShopService;

	@Autowired
	public GuitarController(GuitarShopService guitarShopService) {
		this.guitarShopService = guitarShopService;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String addGuitar(Model model, Principal principal) {

		model.addAttribute(new Guitar());
		return "guitars/form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String addGuitarFromForm(@Valid Guitar guitar, BindingResult bindingResult,
			@RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest servletRequest,
			Principal principal) {
		// add current user to model to write guitar with his id

		User user = guitarShopService.getUser(principal.getName());
		guitar.setUser(user);
		// if there are some mistakes, bindingResult has errors
		if (bindingResult.hasErrors()) {
			// get back to edit page (back)
			return "guitars/form";
		}
		long key = guitarShopService.addGuitar(guitar);
		// check if there is an image
		try {
			if (!image.isEmpty()) {
				// check if image is jpeg
				validateImage(image);
				saveImage("guitars" + File.separator + key + ".jpg", image);
			}
		} catch (ImageUploadException e) {
		}
		// build url from root
		return "redirect:/guitars/" + guitar.getType() + File.separator + guitar.getName() + "-" + key;
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

	@RequestMapping(value = "/{type}/{name}-{id}", method = RequestMethod.GET)
	public String showGuitar(@PathVariable String id, Model model) {

		Guitar guitar = guitarShopService.getGuitar(Integer.parseInt(id));
		model.addAttribute(guitar);

		return "guitars/view";
	}

	// edit guitar's info
	@RequestMapping(value = "/{type}/{name}-{id}/edit", method = RequestMethod.GET)
	public String editGuitar(@PathVariable long id, Model model) {
		Guitar guitar = guitarShopService.getGuitar(id);

		model.addAttribute(guitar);
		return "guitars/edit";
	}

	@RequestMapping(value = "/{type}/{name}-{id}/edit", method = RequestMethod.POST)
	public String editGuitarFromForm(@Valid Guitar guitar, BindingResult bindingResult,
			@RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest servletRequest) {
		// if there are some mistakes, bindingResult has errors
		if (bindingResult.hasErrors()) {
			// get back to edit page (back)
			return "guitars/edit";
		}

		guitarShopService.saveGuitar(guitar);

		// check if there is an image
		try {
			if (!image.isEmpty()) {
				// check if image is jpeg
				validateImage(image);
				// save image and make full name
				saveImage("guitars" + File.separator + guitar.getId() + ".jpg", image);
			}
		} catch (ImageUploadException e) {
		}
		// build url from root
		return "redirect:/guitars/" + guitar.getType() + File.separator + guitar.getName() + "-" + guitar.getId();
	}

	@RequestMapping(value = "/{type}/{name}-{id}/delete", method = RequestMethod.GET)
	public String deleteGuitar(@PathVariable int id) {
		guitarShopService.deleteGuitar(id);
		deleteImage(id + ".jpg");
		return "redirect:/home";
	}

	public void deleteImage(String fileName) {
		String rootPath = System.getProperty("catalina.home");
		String dir = rootPath + File.separator + "images" + File.separator + "guitars";
		File file = new File(dir + File.separator + fileName);
		file.delete();
	}

	@RequestMapping(value="/{type}/{name}-{id}/print", method=RequestMethod.GET)
	public ModelAndView pdfExport(@PathVariable long id) {
	
		Guitar guitar=guitarShopService.getGuitar(id);
		return new ModelAndView("pdfDocument","guitar", guitar);
		
	}
}
