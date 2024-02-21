package com.openXcell.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openXcell.dao.ProductRepository;
import com.openXcell.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Transactional(rollbackFor = Exception.class)
	public void saveProduct(Product product) {
		Product p = productRepository.save(product);
	}

	public Optional<Product> getProductsById(int id) {
		return productRepository.findById(id);
	} 
	
	public List<Product> getAllMovies(){
		return productRepository.findAll();
	}
	
}
