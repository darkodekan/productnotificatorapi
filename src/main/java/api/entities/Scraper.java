package api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="scraper")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Scraper {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	Integer id;
	
	@Column(name="name")
	String name;
	
	@Column(name="ip_address")
	String ipAddress;
	
	@Column(name="last_update")
	@Temporal(TemporalType.TIMESTAMP)
	Date lastUpdate;
	
	
	
	
	
	
	

}
