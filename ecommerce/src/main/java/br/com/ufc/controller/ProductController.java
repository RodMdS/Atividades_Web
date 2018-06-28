package br.com.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Product;
import br.com.ufc.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/insertProduct")
	public ModelAndView formProduct() {
		ModelAndView mv = new ModelAndView("formProduct");
		mv.addObject("product", new Product());
		return mv;
	}
	
	@PostMapping("/add")
	public ModelAndView saveProduct(Product product, @RequestParam(value="image") MultipartFile image) {
		productService.addProduct(product, image);
		
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
	/*@GetMapping("/list")
	public ModelAndView getProducts() {
		List<Product> products = productService.getAllProducts();
		
		ModelAndView mv = new ModelAndView("products");
		mv.addObject("allProducts", products);
		return mv;
	}*/
	
	@RequestMapping("{id}")
	public ModelAndView updateProduct(@PathVariable Long id) {
		Product product = productService.getById(id);
		
		ModelAndView mv = new ModelAndView("formProduct");
		mv.addObject("product", product);
		return mv;
	}
	
	@RequestMapping("/remove/{id}")
	public ModelAndView removeProduct(@PathVariable Long id) {
		productService.rmProduct(id);
		
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
}
