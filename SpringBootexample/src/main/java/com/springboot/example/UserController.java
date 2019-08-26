package com.springboot.example;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties.Headers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	private static  final Logger logger = LoggerFactory.getLogger(UserController.class.getClass());
	
	@RequestMapping(value="/get/{name}",method= RequestMethod.GET)
	@ResponseBody
	public List getUser(HttpServletRequest request,
			@PathVariable("name") String name){
		
		List list=new ArrayList();
		list.add(101);
		list.add("Dinesh");
		list.add(810927);
		
		System.out.println("inside controoler");
		//logger.info("user information by list "+list);;
		
		return list;
		
	}
	
	@RequestMapping(value="/getuser/{id}/{name}/{mobile}",method=RequestMethod.GET)
	public User getUser(@ModelAttribute("user") User user) {
		
		
		logger.info("user infomation by objetc"+user);
		return user;
		
	}
	

}
