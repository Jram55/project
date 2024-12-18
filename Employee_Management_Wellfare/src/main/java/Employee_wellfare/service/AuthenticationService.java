package Employee_wellfare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Employee_wellfare.config.JwtService;
import Employee_wellfare.dto.Login;
import Employee_wellfare.dto.LoginResponse;
import Employee_wellfare.dto.ResetPassword;
import Employee_wellfare.entity.Employee;
import Employee_wellfare.repository.Employeerepository;

@Service
public class AuthenticationService {

	@Autowired
	private Employeerepository emprepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtservice;

	public boolean login(Login loginDto) {
		Optional<Employee> _user = emprepo.findByEmail(loginDto.getEmail());
		if (_user.isPresent()) {
			Employee user = _user.get();
			System.out.println((loginDto.getPassword()));
			if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {

				return true;
			} else {

				return false;
			}
		} else
			return false;
	}

	public LoginResponse getLoginData(Login loginDto) {
		LoginResponse loginResponseDto = new LoginResponse();
		Optional<Employee> users = emprepo.findByEmail(loginDto.getEmail());
		if (users.isPresent()) {
			Employee employee = users.get();
			String roleString = employee.getRole();
			String names = employee.getName();
			long employeeId = employee.getEmployeeId();
			List<String> roles = new ArrayList<>();

			if (roleString != null && !roleString.trim().isEmpty()) {
				// Split the roles by comma
				String[] roleArray = roleString.split(",");
				for (String role : roleArray) {
					roles.add(role.trim()); // Add each trimmed role to the list
				}
			}

			String token = jwtservice.generateToken(employee.getEmail(), roles);
			loginResponseDto.setToken(token);
			loginResponseDto.setRole(roleString);
			loginResponseDto.setName(names);
			loginResponseDto.setEmployeeId(employeeId);// Set the role in the response
			return loginResponseDto;
		} else {
			return null;
		}

	}

	public boolean resetPassword(ResetPassword resetPasswordRequest) {
	    Optional<Employee> optionalEmployee = emprepo.findByEmail(resetPasswordRequest.getEmail());

	    if (optionalEmployee.isPresent()) {
	        Employee employee = optionalEmployee.get();

	        // Encode the new password from the resetPasswordRequest
	        System.out.println(resetPasswordRequest.getNewPassword());
	        String encodedPassword = passwordEncoder.encode(resetPasswordRequest.getNewPassword());

	        // Set the new encoded password
	        employee.setPassword(encodedPassword);

	        // Save the updated employee record
	        emprepo.save(employee);

	        return true; // Password reset successful
	    }

	    return false; // Employee not found
	}

}
