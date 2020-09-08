package com.trungtamjava.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	
 
	@Autowired
	Environment environment;
	
	@GetMapping(value = "/login")
	public String login(HttpServletRequest request,
			@RequestParam(name="e", required = false) String error) {
	         if(error != null){
	        	 request.setAttribute("e", error);
	        	
	         }
		return "login";
	}
}
