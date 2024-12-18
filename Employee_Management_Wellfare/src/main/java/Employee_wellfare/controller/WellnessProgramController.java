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

import Employee_wellfare.dto.WellnessProgramDTO;
import Employee_wellfare.entity.WellnessProgram;
import Employee_wellfare.service.WellnessProgramService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employeewellness")
public class WellnessProgramController {
	
	
	@Autowired
	private WellnessProgramService wellnessservice;
	
	
	@PostMapping("/create")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<WellnessProgram> create(@RequestBody WellnessProgram wellness){
		
		WellnessProgram well=wellnessservice.createWellness(wellness);
		return new ResponseEntity<WellnessProgram>(well,HttpStatus.CREATED);
	}

	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<List<WellnessProgramDTO>> getall(){
		
		List<WellnessProgramDTO> well=wellnessservice.getAllprogram();
		return new ResponseEntity<> (well,HttpStatus.OK);
	}
	
	@GetMapping("/{programID}/details")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<WellnessProgramDTO> getWellnessProgramWithEmployeeDetails(@PathVariable Long programID) {
	    WellnessProgramDTO programDTO = wellnessservice.getWellnessProgramWithEmployees(programID);
	    return new ResponseEntity<>(programDTO, HttpStatus.OK);
	}
	
	
	@PutMapping("/{programID}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<WellnessProgram> upadte(@PathVariable Long programID,@RequestBody WellnessProgram wellness){
		WellnessProgram well=wellnessservice.updateWellnessprogram(programID,wellness);
		return new ResponseEntity<>(well,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("{programID}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public void upadte(@PathVariable Long programID) {
		
		wellnessservice.deleteWellnessprogram(programID);
	}
	
	

}
