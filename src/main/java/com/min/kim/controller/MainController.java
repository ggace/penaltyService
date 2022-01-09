package com.min.kim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/client/main")
	public String home() {
		return "client/main";
	}
}
