package api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name="user")
@Entity
public class User {
	public Integer id;
	
	public String email;
	
	public String password;
	public boolean verified;
	public boolean admin;
	public User() {
		
	}
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	

}
