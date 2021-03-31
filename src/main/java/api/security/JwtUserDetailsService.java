package api.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(getUsername());
		if (getUsername().equals(username)) {
			return new User(getUsername(), "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	public static String getUsername() {
		Properties prop = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream("config.properties");
		    prop.load(fileInputStream);
		    fileInputStream.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return prop.getProperty("admin_username");
	}
	


}
