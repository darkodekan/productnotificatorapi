package api.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.entities.Product;
import api.repositories.ProductRepository;

@Service 
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public Collection<Product> getAll(){
		return productRepository.findAll();
		
	}
	public Product getOne(Integer id) {
		return productRepository.getOne(id);
	}
	
}
