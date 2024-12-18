package Employee_wellfare.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Employee_wellfare.entity.Employee;
import Employee_wellfare.entity.Event;
import Employee_wellfare.entity.EventRegistration;
import Employee_wellfare.repository.Employeerepository;
import Employee_wellfare.repository.EventRegistrationRepository;
import Employee_wellfare.repository.EventRepository;

@Service
public class EventRegistraionService {
	
	@Autowired
	private final Employeerepository emprepo;
	private final EventRepository eventrepo;
	private final EventRegistrationRepository eventregistrationrepo;
	
	
	public EventRegistraionService(Employeerepository emprepo, EventRepository eventrepo,
			EventRegistrationRepository eventregistrationrepo) {
		
		this.emprepo = emprepo;
		this.eventrepo = eventrepo;
		this.eventregistrationrepo = eventregistrationrepo;
	}


	public EventRegistration registerEmployeeinEvent(Long employeeId, Long eventId) {
		
		Employee employee=emprepo.findById(employeeId).orElseThrow(()-> new RuntimeException("Employee Not Found"));
		Event event=eventrepo.findById(eventId).orElseThrow(()-> new RuntimeException("this Event Not Found"));
		
		EventRegistration eventregister=new EventRegistration();
		eventregister.setEmployee(employee);
		eventregister.setEvent(event);
		eventregister.setEventRegistrationDate(new Date());
		eventregister.setEventUnregistrationDate(new Date());
	    
		
		
		return eventregistrationrepo.save(eventregister);
	}
	
	
	

}
