package br.com.ufc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufc.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
