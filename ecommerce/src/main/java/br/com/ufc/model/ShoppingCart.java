package br.com.ufc.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	
	private List<Item> items = new ArrayList<Item>();
	public static List<Product> list = new ArrayList<>();
	public static Double total = 0.0;
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public static List<Product> getList() {
		return list;
	}
	
	public static void setList(List<Product> list) {
		ShoppingCart.list = list;
	}
	
	public static Double getTotal() {
		return total;
	}
	
	public static void setTotal(Double total) {
		ShoppingCart.total = total;
	}

}
