package com.springguitarshop.mvc;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.springguitarshop.service.GuitarShopService;

@Controller

public class HomeController {
	//number of records on home page
	private static final int DEFAULT_GUITARS_PER_PAGE=4;	
	//makes all operations with database
	private GuitarShopService guitarShopService;
	//saved request params
	Map<String, String> savedParams;
	//inject guitarShopService from xml-file while creating a HomeController	
	@Autowired
	public HomeController(GuitarShopService guitarShopService) {
		this.guitarShopService=guitarShopService;
		savedParams=new HashMap<String, String>();		
		setDefaultParams();
	}
	@RequestMapping({"/{filter}/{pageid}", "/", "/home"})
	public String showHomePage(@PathVariable(required=false) String filter, @PathVariable(required=false) Integer pageid, @RequestParam Map<String, String> params, Model model) {
		if(pageid==null) {
			pageid=1;
			filter="home";
		}
		//save params if they are represented (only for custom search)
		saveRequestParams(params, filter);	
		//if this is different filter that it was before(for /home and /type filters)		
		setFilterParams(savedParams, filter);	
		//set pages
		setPages();	
		//add models
		model.addAttribute("guitars", guitarShopService.selectGuitars(savedParams, pageid*DEFAULT_GUITARS_PER_PAGE-DEFAULT_GUITARS_PER_PAGE, DEFAULT_GUITARS_PER_PAGE));	     
		model.addAttribute("pages", savedParams.get("pages"));		
		return "home";
	}
	public Map<String, String> validateParams(Map<String, String> params){		
		// validate params
		if(params.get("name").equals("")) {
			//set it to any value
			 params.put("name", "%");			
		}
		//if it is set
		//add % to sql (in case when it's not full name)
		else {		
			 params.put("name", "%"+params.get("name")+"%");	
		}
		//if type is not defined
		if(params.get("type").equals("")) {
			 params.put("type", "%");		
		}
		//if maxPrice is not set
		if(params.get("maxprice").equals("")) {
			params.put("maxprice", ""+Integer.MAX_VALUE);			
		}
		//if minPrice is not set
		if(params.get("minprice").equals("")) {
			params.put("minprice", ""+ 0);			
		}	
		      
		return params;
	}
	//check are there some request parameters and save them
	public void saveRequestParams(Map<String, String> params, String filter) {
		if(params.size()!=0) {
			//validate request params and them to savedParams
			savedParams=validateParams(params);			
			//add filter name
			savedParams.put("filter", filter);			
		}
	}
	//set saved parameters to default
	public void setDefaultParams() {		
		savedParams.put("name", "%");
		savedParams.put("type", "%");
		savedParams.put("minprice", "0");
		savedParams.put("maxprice", ""+Integer.MAX_VALUE);
		savedParams.put("filter", "home");
		savedParams.put("order", "");
		savedParams.put("pages", null);
	}
	//set values when filter changed
	public void setFilterParams(Map<String, String> savedParams, String filter) {
		if(!savedParams.get("filter").equals(filter)) {
			//if this is guitar type filter
			if(!filter.equals("home")&&!filter.equals("search")) {				
				//save type
				setDefaultParams();
				//set filter in type
				savedParams.put("type", filter);
				savedParams.put("filter", filter);
			}
			//if it's home
			else if(filter.equals("home")) {
				setDefaultParams();				
			}
			//set pages number to null
			savedParams.put("pages", null);
	    }
    }
	
	//set pages
	public void setPages() {		
		if(savedParams.get("pages")==null) {
			int pages=guitarShopService.selectCount(savedParams);
			pages=(int)Math.ceil(pages/(double)DEFAULT_GUITARS_PER_PAGE);	
			savedParams.put("pages", ""+pages );				
		}		
	}
}
	
