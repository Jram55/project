package Employee_wellfare.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Employee_wellfare.dto.Login;
import Employee_wellfare.dto.LoginResponse;
import Employee_wellfare.dto.ResetPassword;
import Employee_wellfare.service.AuthenticationService;





@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	
	@Autowired
	private AuthenticationService authService;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Login request) throws Exception {
	    if (authService.login(request)) {
	        LoginResponse response = authService.getLoginData(request);
	        if (response != null) {
	            
	            Map<String,String> responseBody = new HashMap<>();
	            responseBody.put("jwt", response.getToken());
	            responseBody.put("name", response.getName()); 
	            responseBody.put("employeeId",String.valueOf(response.getEmployeeId()));
	            return ResponseEntity.ok(responseBody);
	        }
	    }
	    
	    // Return an error response if login fails
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid credentials"));
	}
	

	   
	    
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPassword resetPasswordRequest) {
	    if (authService.resetPassword(resetPasswordRequest)) {
	        return ResponseEntity.ok("Password reset successful.");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
	    }
	}
}


