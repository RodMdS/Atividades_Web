package br.com.ufc.model;

public class BuyHistory {
	
	private Long id;
	private String person;
	private String product;
	private int quantity;
	private double value;
	
	public BuyHistory(Long id, String person, String product, int quantity, double value) {
		this.id = id;
		this.person = person;
		this.product = product;
		this.quantity = quantity;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}
