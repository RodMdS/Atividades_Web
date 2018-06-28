package br.com.ufc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Product;
import br.com.ufc.model.ShoppingCart;
import br.com.ufc.service.ShopppingCartService;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

	@Autowired
	private ShopppingCartService shopppingCartService;
	
	ShoppingCart shoppingCart = new ShoppingCart();
	
	@RequestMapping("/addProduct/{id}")
	public ModelAndView addItem(@PathVariable Long id) {
		Product product = this.shopppingCartService.getById(id);
		ModelAndView mv = new ModelAndView("redirect:/");

		for(Product elem : this.shoppingCart.list) {
			if(elem.getId().equals(product.getId())) {
				elem.setContains_list(true);
				elem.setQuantity(elem.getQuantity() + 1);
				this.shoppingCart.total += elem.getPrice();
				return mv;
			}
		}
		this.shopppingCartService.addProduct(product);		
		return mv;
	}
	
	@GetMapping("/visualize")
	public ModelAndView visualizar() {
		ModelAndView mv = new ModelAndView("shoppingCart");
		mv.addObject("productsList", this.shoppingCart.list);
		mv.addObject("total", this.shoppingCart.total);
		return mv;
	}
	
	@RequestMapping("/remove/{id}")
	public ModelAndView remove(@PathVariable Long id) {
		this.shopppingCartService.removeProduct(id);
		ModelAndView mv = new ModelAndView("redirect:/shoppingcart/visualize");		
		return mv;
	}
	
	@RequestMapping("/buy")
	public ModelAndView buy() {
		this.shopppingCartService.buyProduct();		
		ModelAndView mv = new ModelAndView("redirect:/shoppingcart/purchaseshistoric");
		return mv;
	}
	
	@RequestMapping("/purchaseshistoric")
	public ModelAndView purchasesHistoric() {
		ModelAndView mv = new ModelAndView("purchasesHistoric");
		mv.addObject("allBuys", this.shopppingCartService.purchasesHistoric());
		return mv;
	}
	
}
