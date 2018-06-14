package br.com.ufc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/beautymakeup")
	public String startPage() {
		return "index";
	}
	
	@RequestMapping("/erro")
	public String errorPage() {
		return "error";
	}
	
}
