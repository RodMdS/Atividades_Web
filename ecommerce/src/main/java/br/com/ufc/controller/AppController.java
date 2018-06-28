package br.com.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Product;
import br.com.ufc.service.ProductService;

@Controller
public class AppController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/")
	public ModelAndView getProducts() {
		List<Product> products = productService.getAllProducts();
		
		ModelAndView mv = new ModelAndView("products");
		mv.addObject("allProducts", products);
		return mv;
	}
	
	@RequestMapping("/erro")
	public String errorPage() {
		return "error";
	}
	
}
