package Employee_wellfare.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Employee_wellfare.dto.EnrollmentRequest;
import Employee_wellfare.entity.WellnessEnrollment;
import Employee_wellfare.service.WellnessEnrollmentService;

@RestController
@RequestMapping("/enrollment")
public class WellnessEnrollmentController {

	@Autowired
	private WellnessEnrollmentService wellnessEnrollmentService;

	@PostMapping("/enroll")
	@PreAuthorize("hasAnyAuthority( 'ROLE_USER')")
	public ResponseEntity<WellnessEnrollment> enrollEmployee(@RequestBody EnrollmentRequest enrollmentRequest) {
		WellnessEnrollment enrollment = wellnessEnrollmentService.enrollEmployeeInProgram(enrollmentRequest.getEmployeeId(),enrollmentRequest.getProgramID());
		return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
	}
	
//	@GetMapping("/employee/{employeeId}")
//	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
//	public ResponseEntity<List<WellnessEnrollment>> getEnrollmentsByEmployeeId(@PathVariable Long employeeId) {
//		List<WellnessEnrollment> enrollments = wellnessEnrollmentService.getEnrollmentsByEmployeeId(employeeId);
//		return new ResponseEntity<>(enrollments, HttpStatus.OK);
//	}
	
	@GetMapping("/employee/{employeeId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Map<String, Object>>> getEnrollmentsByEmployeeId(@PathVariable Long employeeId) {
	    List<Map<String, Object>> enrollmentDetails = wellnessEnrollmentService.getEnrollmentsByEmployeeId(employeeId);
	    return new ResponseEntity<>(enrollmentDetails, HttpStatus.OK);
	}




}
