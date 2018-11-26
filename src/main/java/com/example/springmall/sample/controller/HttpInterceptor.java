package com.example.springmall.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Controller
public class HttpInterceptor extends HandlerInterceptorAdapter{
	private static final Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);
	@RequestMapping(value="/addmember", method=RequestMethod.GET)
	public String requestMethodName() {
		return "/sample/NewFile";
	}
	

}
