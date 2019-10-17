package com.hehe.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@RequestMapping("/hehe")
	public String hello() {
		System.out.println("requestmapping init");
		return "Hello Spring MVC";
	}
}
