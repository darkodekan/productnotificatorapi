package api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import api.entities.Scraper;

public interface ServerRepository extends JpaRepository<Scraper, Integer> {


}
