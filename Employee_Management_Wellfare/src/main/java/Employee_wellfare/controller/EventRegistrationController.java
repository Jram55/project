package Employee_wellfare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import Employee_wellfare.dto.EventRegistrationRequest;
import Employee_wellfare.entity.EventRegistration;
import Employee_wellfare.service.EventRegistraionService;

@RestController
@RequestMapping("/event")
public class EventRegistrationController {
	
	@Autowired
	private EventRegistraionService eventregisterservice;
	
	
	@PostMapping("/register")
	public ResponseEntity<EventRegistration> registerEvent(@RequestBody EventRegistrationRequest eventrequest){
		
		EventRegistration event=eventregisterservice.registerEmployeeinEvent(eventrequest.getEmployeeId(),eventrequest.getEventId());
		
		return new ResponseEntity<EventRegistration>(event,HttpStatus.CREATED);
	}

}
