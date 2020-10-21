package com.ecomerce.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.webapp.entity.Product;
import com.ecomerce.webapp.exception.ProductNotFound;
import com.ecomerce.webapp.repository.ProductRepository;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	//inject dependency
	@Autowired
	private ProductRepository productRepository;
	
	//GET all Products
	@GetMapping("/products")
	public List<Product> getAllProduct(){
		return this.productRepository.findAll();
	}
	
	//Get product by id
	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable(value="id") long productId){
		return this.productRepository.findById(productId)
				.orElseThrow(()-> new ProductNotFound("Product not found with id" +productId));	
	}
	
	//create product
		@PostMapping
		public Product addProduct(@RequestBody Product product) {
			return this.productRepository.save(product);
		}
	
}
