package Employee_wellfare.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;

import Employee_wellfare.entity.Employee;



public class UserInfoDeatils implements UserDetails{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1721999256949211194L;

	private String email;
	
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserInfoDeatils(Employee emp) {
		this.email=emp.getEmail();
	    this.password=emp.getPassword();
	     this.authorities=Arrays.stream(emp.getRole().split(","))
	    		 .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
	    		  .map(SimpleGrantedAuthority::new)
	    		  .collect(Collectors.toList());
	    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
	
		return this.password;
	}

	@Override
	public String getUsername() {
		
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
	
		return true;
	}

}
