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

import Employee_wellfare.dto.ChallengeDto;
import Employee_wellfare.entity.Challenge;
import Employee_wellfare.service.ChallengeService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/challenge")
public class ChallengeController {
	
	
	@Autowired
	private ChallengeService challengeservice;
	
	@PostMapping("/create")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<Challenge> create(@RequestBody Challenge challenge){
		
		Challenge chall=challengeservice.create(challenge);
		return new ResponseEntity<Challenge>(chall,HttpStatus.CREATED);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
     public ResponseEntity<List<ChallengeDto>> getall(){
		
		List<ChallengeDto> chall=challengeservice.getAllChallenge();
		return new ResponseEntity<>(chall,HttpStatus.OK);
	}
	
	@GetMapping("/{challengeId}/details")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<ChallengeDto> getcahllenge(@PathVariable Long challengeId ){
		
		ChallengeDto chall=challengeservice.getChallengeEmployeedetails(challengeId);
		return new ResponseEntity<ChallengeDto>(chall,HttpStatus.OK);
	}
	

	@PutMapping("/{challengeId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<Challenge> update(@PathVariable Long challengeId,@RequestBody Challenge challenge){
		
		Challenge chall=challengeservice.upadteChallenge(challengeId,challenge);
		return new ResponseEntity<>(chall,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{challengeId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public void delete(@PathVariable Long challengeId) {
		
		challengeservice.deleteChallenge(challengeId);
	}
}
