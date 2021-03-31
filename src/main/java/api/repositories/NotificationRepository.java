package api.repositories;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.entities.Notification;
import api.entities.Product;
import api.entities.Scraper;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
	@Transactional
	Collection<Product> findByProductId(String productId);
	@Transactional
	void deleteByProductId(String productId);
	@Transactional
	Boolean existsByProductId(String productId);
	@Transactional
	Collection<Product> findByServer(Scraper server);
	
}
