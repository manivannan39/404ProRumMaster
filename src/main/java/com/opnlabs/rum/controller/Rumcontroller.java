package com.opnlabs.rum.controller;

import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class Rumcontroller {
	
	
	@Autowired
	private MongoTemplate mongotemplate;
	
	
	
	private static final Logger Logger = LogManager.getLogger(Rumcontroller.class);
	
	@RequestMapping(value = "/name", method = RequestMethod.GET)
	public String getname() {

		return "this is my first spring boot application";
	}

	@RequestMapping(value = "/setname", method = RequestMethod.GET)
	public String setname(RedirectAttributes redir) {
		String name = "this is my name";
		redir.addAttribute(name);
		return "redirect:/getname2";
	}

	@RequestMapping(value = "/getname2", method = RequestMethod.GET)
	public String getname2(Model model) {
		model.containsAttribute(getname());
		return null;

	}

	@GetMapping("/rumdata")
	public String getRumdata(@RequestParam String data) {
		
		
		Logger.info("REQUEST RECEIVED TO RUM API");

		byte[] decodedBytes = Base64.getDecoder().decode(data);
		String decodedString = new String(decodedBytes);
		try {
			Document doc = Document.parse(decodedString);
			mongotemplate.insert(doc, "RUM");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(decodedString + "\n");
		return "success";
	}

}
