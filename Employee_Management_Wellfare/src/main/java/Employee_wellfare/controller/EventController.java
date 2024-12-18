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

import Employee_wellfare.dto.EventDto;
import Employee_wellfare.entity.Event;
import Employee_wellfare.service.EventService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/event")
public class EventController {
       
	@Autowired
	private EventService eventservice;
	
	
	@GetMapping()
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<List<EventDto>> getAll() {
		List<EventDto> event=eventservice.getAllEvent();
		return new ResponseEntity<>(event,HttpStatus.OK);
	}
	
	
	@PostMapping("/create")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<Event> create(@RequestBody Event event){
		
		Event events=eventservice.createEvent(event);
		return new ResponseEntity<Event>(events,HttpStatus.CREATED);
	}
	
	@GetMapping("/{eventId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<EventDto> eventregister(@PathVariable Long eventId) {

		EventDto event = eventservice.getEventRegisterEmployee(eventId);
		return new ResponseEntity<EventDto>(event, HttpStatus.OK);
	}
	
	@PutMapping("/{eventId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<Event> update(@PathVariable Long eventId, @RequestBody Event event) {

		Event events = eventservice.upadteEvent(eventId, event);
		return new ResponseEntity<Event>(events, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{eventId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public void delete(@PathVariable Long eventId) {

		eventservice.deleteEvent(eventId);
	}

	

}
