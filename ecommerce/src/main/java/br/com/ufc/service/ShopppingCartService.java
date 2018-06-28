package br.com.ufc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.ufc.model.Buy;
import br.com.ufc.model.BuyHistory;
import br.com.ufc.model.Person;
import br.com.ufc.model.Product;
import br.com.ufc.model.ShoppingCart;
import br.com.ufc.repository.BuyRepository;
import br.com.ufc.repository.PersonRepository;
import br.com.ufc.repository.ProductRepository;

@Service
public class ShopppingCartService {
	
	ShoppingCart shoppingCart = new ShoppingCart();
	
	@Autowired
	private BuyRepository buyRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PersonRepository  personRepository;
	
	public void addProduct(Product product) {
		product.setContains_list(true);
		this.shoppingCart.list.add(product);
		this.shoppingCart.total += product.getPrice();
	}
	
	public Product getById(Long id) {
		return this.productRepository.getOne(id);
	}
	
	public void removeProduct(Long id) {
		for(int i = 0; i < this.shoppingCart.list.size(); i++) {
			if(this.shoppingCart.list.get(i).getId().equals(id)) {
				this.shoppingCart.total -= this.shoppingCart.list.get(i).getPrice() * 
						this.shoppingCart.list.get(i).getQuantity();
				this.shoppingCart.list.remove(i);
			}
		}
	}
	
	public void buyProduct() {
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;
		Person person = personRepository.findByLogin(user.getUsername());
		
		for(Product product : this.shoppingCart.list) {
			if(product.isContains_list()) {
				Buy buy = new Buy(person.getId(), product.getId(), product.getQuantity(),
						(double) (product.getPrice() * product.getQuantity()));
				this.buyRepository.save(buy);
			}
		}
		clearShoppingCart();
	}
	
	public List<BuyHistory> purchasesHistoric() {
		List<Buy> purchases = new ArrayList<Buy>();
		List<BuyHistory> purchasesHistoric = new ArrayList<BuyHistory>();
		purchases = this.buyRepository.findAll();
		
		for(Buy buy : purchases) {
			Person person = this.personRepository.getOne(buy.getPerson_id());
			Product product = this.productRepository.getOne(buy.getProduct_id());
			BuyHistory buyHistory = new BuyHistory(buy.getId(), person.getName(), 
					product.getName(), buy.getQuantity(), buy.getValue());
			purchasesHistoric.add(buyHistory);
		}
		return purchasesHistoric;
	}
	
	private void clearShoppingCart() {
		this.shoppingCart.list.clear();
		this.shoppingCart.total = 0.0;
	}
	
	
}
