package Employee_wellfare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import Employee_wellfare.dto.EmployeeDTO;
import Employee_wellfare.dto.EventDto;

import Employee_wellfare.entity.Employee;
import Employee_wellfare.entity.Event;
import Employee_wellfare.repository.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventrepo;

	public List<EventDto> getAllEvent() {
		
		List<Event> event= eventrepo.findAll();
		
		
		return event.stream()
                .map(program -> {
                    EventDto dto = new EventDto();
                    dto.setEventId(program.getEventId());
                    dto.setEventName(program.getEventName());
                    dto.setDescription(program.getDescription());
                    dto.setEventType(program.getEventType());
                    dto.setEventDate(program.getEventDate());
                    dto.setHostName(program.getHostName());
                    dto.setEventStatus(program.getEventStatus());
                   
                    return dto;
                })
                .collect(Collectors.toList());
}
	
	

	public Event createEvent(Event event) {
		
		return eventrepo.save(event);
	}
	
	public EventDto getEventRegisterEmployee(Long eventId) {
		Event events=eventrepo.findById(eventId).orElse(null);
		
		EventDto event=new EventDto();
		event.setEventId(events.getEventId());
		event.setEventName(events.getEventName());
		event.setDescription(events.getDescription());
		event.setEventType(events.getEventType());
		event.setEventDate(events.getEventDate());
		
		event.setHostName(events.getHostName());
		event.setEventStatus(events.getEventStatus());
			
		List<EmployeeDTO> employeedto=events.getEventregistration().stream()
				.map(register->{
					Employee employee=register.getEmployee();
					EmployeeDTO emp=new EmployeeDTO();
					emp.setEmployeeId(employee.getEmployeeId());
					emp.setName(employee.getName());
					emp.setPassword(employee.getPassword());
					emp.setRole(employee.getRole());
					emp.setContactNumber(employee.getContactNumber());
					emp.setEmail(employee.getEmail());
					emp.setAge(employee.getAge());
					emp.setGender(employee.getGender());
					emp.setBloodgroup(employee.getBloodgroup());
					emp.setBmi(employee.getBmi());
					emp.setHobbies(employee.getHobbies());
					emp.setDepartment(employee.getDepartment());
					return emp;
					
				}).collect(Collectors.toList());
				event.setEventregister(employeedto);
				return event; 
		}
	
public Event upadteEvent(Long eventId, Event event) {
		
		Event events=eventrepo.findById(eventId).orElse(null);
		
		events.setEventName(event.getEventName());
		events.setDescription(event.getDescription());
		events.setEventType(event.getEventType());
		events.setEventDate(event.getEventDate());
		events.setHostName(event.getHostName());
		events.setEventStatus(event.getEventStatus());
		
		return eventrepo.save(event);
	}

	public void deleteEvent(Long eventId) {
		
		eventrepo.deleteById(eventId);
		
	}

}
