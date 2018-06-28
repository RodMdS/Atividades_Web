package br.com.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ufc.model.Product;
import br.com.ufc.repository.ProductRepository;
import br.com.ufc.util.FileUtil;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public void addProduct(Product product, MultipartFile image) {
		String address = "images/" + product.getName() + ".png";
		product.setAddress(address);
		FileUtil.saveImage(address, image);
		productRepository.save(product);
	}
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public Product getById(Long id) {
		return productRepository.getOne(id);
	}
	
	public void rmProduct(Long id) {
		FileUtil.removeImage(productRepository.getOne(id).getAddress());
		this.productRepository.delete(id);
	}
	
}
