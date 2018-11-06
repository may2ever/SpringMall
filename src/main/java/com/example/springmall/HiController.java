package com.example.springmall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
	@Autowired Mapper mapper;
	@RequestMapping("/hi")
	public String hi() {
		System.out.println("hi Spring Boot!");
		System.out.println(mapper.totalCount());
		return "index";
	}
}
