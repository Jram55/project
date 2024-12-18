package Employee_wellfare.service;


import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import Employee_wellfare.dto.ChallengeDto;
import Employee_wellfare.dto.ChallengeParticipationRequest;
import Employee_wellfare.dto.EmployeeDTO;
import Employee_wellfare.dto.EnrollmentRequest;
import Employee_wellfare.dto.EventDto;
import Employee_wellfare.dto.EventRegistrationRequest;

import Employee_wellfare.dto.WellnessProgramDTO;
import Employee_wellfare.entity.Employee;

import Employee_wellfare.repository.Employeerepository;

@Service
public class EmployeeService {

	@Autowired
	private Employeerepository emprepo;

	@Autowired
	private PasswordEncoder passwordEncoder;


	public Employee createuser(Employee emp) {
		emp.setPassword(passwordEncoder.encode(emp.getPassword()));
		return emprepo.save(emp);

	}

	

	public List<EmployeeDTO> getAlluser() {
		

		 List<Employee> employees =  emprepo.findAll();
	        
	        // Map Employee entities to EmployeeDTOs
	        return employees.stream()
	                .map(employee ->{
	                	EmployeeDTO dto=new EmployeeDTO();
	                
	                	dto.setEmployeeId(employee.getEmployeeId());
	                	dto.setName(employee.getName());
	                	dto.setRole( employee.getRole());
	                	dto.setContactNumber( employee.getContactNumber());
	                	dto.setEmail(  employee.getEmail());
	                	dto.setAge(  employee.getAge());
	                	dto.setGender(  employee.getGender());
	                	dto.setBloodgroup(   employee.getBloodgroup());
	                	dto.setBmi(  employee.getBmi());
	                	dto.setHobbies(employee.getHobbies());
	                	dto.setDepartment(   employee.getDepartment());
	                	return dto;
	                })
	                .collect(Collectors.toList());
	    }

	public EmployeeDTO getByuserId(Long empId) {
	    Employee employee = emprepo.findById(empId).orElse(null);
	    
	    if (employee == null) {
	        return null; // or throw an exception depending on your requirement
	    }

	    // Map the employee entity to DTO
	    EmployeeDTO dto = new EmployeeDTO();
	    dto.setEmployeeId(employee.getEmployeeId());
	    dto.setName(employee.getName());
	    dto.setPassword(employee.getPassword());
	    dto.setRole(employee.getRole());
	    dto.setContactNumber(employee.getContactNumber());
	    dto.setEmail(employee.getEmail());
	    dto.setAge(employee.getAge());
	    dto.setGender(employee.getGender());
	    dto.setBloodgroup(employee.getBloodgroup());
	    dto.setBmi(employee.getBmi());
	    dto.setHobbies(employee.getHobbies());
	    dto.setDepartment(employee.getDepartment());

	    // Map the wellness enrollments
	    dto.setWellnessEnrollments(employee.getWellnessEnrollments().stream().map(enrollment -> {
	        EnrollmentRequest enrollmentDTO = new EnrollmentRequest();
	        enrollmentDTO.setEmployeeId(enrollment.getEnrollmentid());
	        enrollmentDTO.setProgramID(enrollment.getWellnessProgram().getProgramID());
	        enrollmentDTO.setEnrollmentid(enrollment.getEnrollmentid());
	        enrollmentDTO.setEnrollDate(enrollment.getEnrollDate());
	        enrollmentDTO.setWellnessEnrollmentStatus(enrollment.getWellnessEnrollmentStatus());

	        WellnessProgramDTO programDTO = new WellnessProgramDTO();
	        programDTO.setProgramID(enrollment.getWellnessProgram().getProgramID());
	        programDTO.setWellnessProgramName(enrollment.getWellnessProgram().getWellnessProgramName());
	        programDTO.setWellnessProgramStartDate(enrollment.getWellnessProgram().getWellnessProgramStartDate());
	        programDTO.setWellnessProgramEndDate(enrollment.getWellnessProgram().getWellnessProgramEndDate());
	        programDTO.setCategory(enrollment.getWellnessProgram().getCategory());
	        programDTO.setDescription(enrollment.getWellnessProgram().getDescription());
	        programDTO.setInstructorName(enrollment.getWellnessProgram().getInstructorName());
	        programDTO.setWellnessProgramStatus(enrollment.getWellnessProgram().getWellnessProgramStatus());

	        enrollmentDTO.setWellnessProgram(programDTO);
	        return enrollmentDTO;
	         }).collect(Collectors.toList()));
	    
	    dto.setChallengeparipation(employee.getChallengeparipation().stream().map(participation -> {
            ChallengeParticipationRequest participationDTO = new ChallengeParticipationRequest();
            participationDTO.setParticipationId(participation.getParticipationId());
            participationDTO.setParticipationDate(participation.getParticipationDate());
            participationDTO.setProgress(participation.getProgress());

            ChallengeDto challengeDTO = new ChallengeDto();
            challengeDTO.setChallengeId(participation.getChallenge().getChallengeId());
            challengeDTO.setChallengeName(participation.getChallenge().getChallengeName());
            challengeDTO.setDescription(participation.getChallenge().getDescription());
            challengeDTO.setChallengeType(participation.getChallenge().getChallengeType());
            challengeDTO.setChallengeStartDate(participation.getChallenge().getChallengeStartDate());
            challengeDTO.setChallengeEndDate(participation.getChallenge().getChallengeEndDate());
            challengeDTO.setScore(participation.getChallenge().getScore());

            participationDTO.setChallenge(challengeDTO);
            return participationDTO;
        }).collect(Collectors.toList()));

        // Map event registrations
        dto.setEventRegistrations(employee.getEventRegistrations().stream().map(registration -> {
            EventRegistrationRequest registrationDTO = new EventRegistrationRequest();
            registrationDTO.setEventRegistrationId(registration.getEventRegistrationId());
            registrationDTO.setEventRegistrationDate(registration.getEventRegistrationDate());
            registrationDTO.setEventUnregistrationDate(registration.getEventUnregistrationDate());

            EventDto eventDTO = new EventDto();
            eventDTO.setEventId(registration.getEvent().getEventId());
            eventDTO.setEventName(registration.getEvent().getEventName());
            eventDTO.setDescription(registration.getEvent().getDescription());
            eventDTO.setEventType(registration.getEvent().getEventType());
            eventDTO.setEventDate(registration.getEvent().getEventDate());
            eventDTO.setHostName(registration.getEvent().getHostName());
            eventDTO.setEventStatus(registration.getEvent().getEventStatus());

            registrationDTO.setEvent(eventDTO);
            return registrationDTO;
        }).collect(Collectors.toList()));

        return dto;
    }



	public Employee upadteEmployee(Long empId, Employee emp) {
		Employee emp1 = emprepo.findById(empId).orElse(null);

		emp1.setName(emp.getName());
		emp1.setEmail(emp.getEmail());
		emp1.setAge(emp.getAge());
		emp1.setGender(emp.getGender());
		emp1.setBloodgroup(emp.getBloodgroup());
		emp1.setBmi(emp.getBmi());
		emp1.setDepartment(emp.getDepartment());

		return emprepo.save(emp1);
	}

	public void deleteEmployee(Long empId) {

		emprepo.deleteById(empId);

	}

}
