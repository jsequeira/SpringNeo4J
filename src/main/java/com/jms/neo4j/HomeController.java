package com.jms.neo4j;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jms.neo4j.model.Street;
import com.jms.neo4j.service.StreetsService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	StreetsService service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate + " TEST" );
		
		service.setDefaultData();
		
		service.testFind();
		
		
		return "home";
	}
	
	@RequestMapping(value = "/{start}/{end}", method = RequestMethod.GET)
	public @ResponseBody String findPath(@PathVariable String start, @PathVariable String end) {
		logger.info("Welcome home! the client locale is ");
		
		
		service.setDefaultData();
		
		service.testFind();
		
		
		
		return service.findShortestPath(start, end).toString();
	}

}
