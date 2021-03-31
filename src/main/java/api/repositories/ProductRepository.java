package api.repositories;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.entities.Product;
import api.entities.Scraper;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Transactional
	Collection<Product> findByProductId(String productId);
	@Transactional
	void deleteByProductId(String productId);
	@Transactional
	Boolean existsByProductId(String productId);
	@Transactional
	Collection<Product> findByServer(Scraper server);
	

}
