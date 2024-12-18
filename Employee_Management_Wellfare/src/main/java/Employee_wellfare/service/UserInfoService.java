package Employee_wellfare.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Employee_wellfare.config.UserInfoDeatils;
import Employee_wellfare.entity.Employee;

import Employee_wellfare.repository.Employeerepository;




@Service
public class UserInfoService implements UserDetailsService{
	
	@Autowired
	private Employeerepository emprepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	 
		Optional<Employee> user=emprepo.findByEmail(email);
		
		if(user.isPresent()) {
			return new UserInfoDeatils(user.get());
		}
		 throw new UsernameNotFoundException("User not found with username: " + email);
	}

}
