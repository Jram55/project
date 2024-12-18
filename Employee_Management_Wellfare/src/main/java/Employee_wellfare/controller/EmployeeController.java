package Employee_wellfare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Employee_wellfare.dto.EmployeeDTO;
import Employee_wellfare.entity.Employee;
import Employee_wellfare.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	@Autowired
	private EmployeeService empservice;
	
	@PostMapping("/new")
	
	public ResponseEntity<Employee> adduser(@RequestBody @Valid Employee emp){
		
		Employee employee=empservice.createuser(emp);
		return new ResponseEntity<Employee>(employee,HttpStatus.CREATED);
	}
	
	@GetMapping("/employees")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('permission:read')")
	public ResponseEntity<List<EmployeeDTO>> getAlluser(){
		
		
		List<EmployeeDTO> employee=empservice.getAlluser();
		return new ResponseEntity<>(employee,HttpStatus.OK);
		
	}

	@GetMapping("/employee/{employeeId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<EmployeeDTO> getBysId(@PathVariable Long employeeId) {
	    EmployeeDTO employeeDTO = empservice.getByuserId(employeeId);
	    
	    if (employeeDTO == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
	}

	@PutMapping("/{employeeId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
     public ResponseEntity<Employee> updateemployee(@PathVariable Long employeeId,@RequestBody Employee emp){
		
		 Employee employee=empservice.upadteEmployee(employeeId,emp);
		 return new ResponseEntity<>(employee,HttpStatus.ACCEPTED);
	}
    	 
     @DeleteMapping("/{employeeId}")
     @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     public void deleteemployee(@PathVariable Long employeeId) {
    	 empservice.deleteEmployee(employeeId);
    	 
     }
	
	
}
