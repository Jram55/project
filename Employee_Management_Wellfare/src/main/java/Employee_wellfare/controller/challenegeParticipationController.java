package Employee_wellfare.controller;

import java.util.List;

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

import Employee_wellfare.dto.ChallengeParticipationRequest;
import Employee_wellfare.entity.ChallengeParticipation;

import Employee_wellfare.service.ChallengeParticipationService;

@RestController
@RequestMapping("/challenge")

public class challenegeParticipationController {

	@Autowired
	private ChallengeParticipationService challengeparticipationservice;

	@PostMapping("/participation")
	@PreAuthorize("hasAnyAuthority( 'ROLE_USER')")
	public ResponseEntity<ChallengeParticipation> participationEmployee(
			@RequestBody ChallengeParticipationRequest challengerequest) {
		ChallengeParticipation chall = challengeparticipationservice
				.participationEmployeeInProgram(challengerequest.getEmployeeId(), challengerequest.getChallengeId());
		return new ResponseEntity<>(chall, HttpStatus.CREATED);
	}
	
	@GetMapping("/employee/{employeeId}")
	@PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN')")
	public ResponseEntity<List<ChallengeParticipation>> getparticipationEmployeeId(@PathVariable Long employeeId){
		
		List<ChallengeParticipation> chall=challengeparticipationservice.getParticipationEmployeeId(employeeId);
		return new ResponseEntity<>(chall,HttpStatus.OK);
	}

}
