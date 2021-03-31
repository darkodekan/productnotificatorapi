package api.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="notification")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Notification {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	Integer id;
	
	@ManyToOne
	@JoinColumn(name="product")
	Product product;
	
	@Column(name="message")
	String content;
	
	Date datetime;
	
	
	
	
}
