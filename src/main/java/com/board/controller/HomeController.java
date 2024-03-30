package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	//  http://localhost:9090
	@RequestMapping("/")
	public String home() {
		
		return "home";
	}
	
}
