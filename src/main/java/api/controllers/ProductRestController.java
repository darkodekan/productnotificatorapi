package api.controllers;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api.entities.Product;
import api.entities.Scraper;
import api.repositories.ProductRepository;
import api.repositories.ServerRepository;

@RestController
@RequestMapping("product")
public class ProductRestController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ServerRepository serverRepository;
	@GetMapping("")
	public Collection<Product> getAllProducts(){
		return productRepository.findAll();
	}
	@GetMapping("{id}")
	public Product getOneProduct(@PathVariable("id") Integer id) {
		return productRepository.getOne(id);
	}
	@GetMapping("server/{id}")
	public Collection<Product> getAllProductsForServer(@PathVariable("id") Integer id){
		return productRepository.findByServer(serverRepository.getOne(id));
	}
	
	@GetMapping("product_id/{productId}")
	public Product getOneProductForProductId(@PathVariable("productId") String productId) {
		return productRepository.findByProductId(productId).iterator().next();
	}
	
	@PutMapping("product_id/{productId}")
	public ResponseEntity<HttpStatus> updateByProductId(@PathVariable("productId") String productId, @RequestBody Product product){
		if(productRepository.existsByProductId(productId)) {
			Scraper server = productRepository.getOne(productRepository.findByProductId(productId).iterator().next().getId()).getServer();
			server.setUpdate(new Date());
			serverRepository.save(server);
			Integer id = productRepository.findByProductId(productId).iterator().next().getId();
			product.setId(id);
			productRepository.save(product);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("product_id/{productId}")
	public ResponseEntity<HttpStatus> deleteOneProductForProductId(@PathVariable("productId") String productId) {
		if(productRepository.existsByProductId(productId)) {
			Scraper server = productRepository.getOne(productRepository.findByProductId(productId).iterator().next().getId()).getServer();
			productRepository.deleteByProductId(productId);
			server.setUpdate(new Date());
			serverRepository.save(server);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("")
	public ResponseEntity<HttpStatus> addProduct(@RequestBody Product product){
		if(productRepository.existsByProductId(product.getProductId())) {
			return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);	
		}
		productRepository.save(product);
		Scraper server = product.getServer();
		server.setUpdate(new Date());
		serverRepository.save(server);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<HttpStatus> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product){
		if(productRepository.existsById(id)) {
			product.setId(id);
			productRepository.save(product);
			
			Scraper server = product.getServer();
			server.setUpdate(new Date());
			serverRepository.save(server);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Integer id){
		if(productRepository.existsById(id)) {
			Scraper server = productRepository.getOne(id).getServer();
			productRepository.deleteById(id);
			server.setUpdate(new Date());
			serverRepository.save(server);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
	
}
