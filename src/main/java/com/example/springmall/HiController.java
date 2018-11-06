package com.example.springmall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HiController {
	
	@RequestMapping("/hi")
	public String hi(Model model) {
		System.out.println("hi Spring Boot!");
	
		return "index"; //forward -> WEB-INF/views/index.jsp
	}
}
