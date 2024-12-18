package Employee_wellfare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Employee_wellfare.dto.EmployeeDTO;
import Employee_wellfare.dto.WellnessProgramDTO;
import Employee_wellfare.entity.Employee;
import Employee_wellfare.entity.WellnessProgram;
import Employee_wellfare.repository.WellnessProgramRepository;

@Service
public class WellnessProgramService {
	
	@Autowired
	private WellnessProgramRepository wellnessrepo;

	public WellnessProgram createWellness(WellnessProgram wellness) {
		
		return wellnessrepo.save(wellness);
	}

	public List<WellnessProgramDTO> getAllprogram() {
		
		List<WellnessProgram> programs= wellnessrepo.findAll();
		 return programs.stream()
	                .map(program -> {
	                    WellnessProgramDTO dto = new WellnessProgramDTO();
	                    dto.setProgramID(program.getProgramID());
	                    dto.setWellnessProgramName(program.getWellnessProgramName());
	                    dto.setWellnessProgramStartDate(program.getWellnessProgramStartDate());
	                    dto.setWellnessProgramEndDate(program.getWellnessProgramEndDate());
	                    dto.setCategory(program.getCategory());
	                    dto.setDescription(program.getDescription());
	                    dto.setInstructorName(program.getInstructorName());
	                    dto.setWellnessProgramStatus(program.getWellnessProgramStatus());
	                    return dto;
	                })
	                .collect(Collectors.toList());
	}


	public WellnessProgram updateWellnessprogram(Long programID, WellnessProgram wellness) {
		WellnessProgram well=wellnessrepo.findById(programID).orElse(null);
		
		well.setWellnessProgramName(wellness.getWellnessProgramName());
		well.setWellnessProgramStartDate(wellness.getWellnessProgramStartDate());
		well.setWellnessProgramEndDate(wellness.getWellnessProgramEndDate());;
		well.setCategory(wellness.getCategory());
		well.setDescription(wellness.getDescription());
		well.setInstructorName(wellness.getInstructorName());
		well.setWellnessProgramStatus(well.getWellnessProgramStatus());;
		return wellnessrepo.save(well);
	}

	public void deleteWellnessprogram(Long programID) {
		
		wellnessrepo.deleteById(programID);
		
	}

	public WellnessProgramDTO getWellnessProgramWithEmployees(Long programID) {
	    WellnessProgram program = wellnessrepo.findById(programID)
	            .orElseThrow(() -> new RuntimeException("Wellness Program not found"));

	    // Map WellnessProgram to WellnessProgramDTO
	    WellnessProgramDTO dto = new WellnessProgramDTO();
	    dto.setProgramID(program.getProgramID());
	    dto.setWellnessProgramName(program.getWellnessProgramName());
	    dto.setWellnessProgramStartDate(program.getWellnessProgramStartDate());
	    dto.setWellnessProgramEndDate(program.getWellnessProgramEndDate());
	    dto.setCategory(program.getCategory());
	    dto.setDescription(program.getDescription());
	    dto.setInstructorName(program.getInstructorName());
	    dto.setWellnessProgramStatus(program.getWellnessProgramStatus());

	    // Map Employee details
	    List<EmployeeDTO> employeeDTOs = program.getWellnessEnrollments().stream()
	            .map(enrollment -> {
	                Employee employee = enrollment.getEmployee();
	                EmployeeDTO empDTO = new EmployeeDTO();
	                empDTO.setEmployeeId(employee.getEmployeeId());
	                empDTO.setName(employee.getName());
	                empDTO.setPassword(employee.getPassword());
	                empDTO.setRole(employee.getRole());
	                empDTO.setContactNumber(employee.getContactNumber());
	                empDTO.setEmail(employee.getEmail());
	                empDTO.setAge(employee.getAge());
	                empDTO.setGender(employee.getGender());
	                empDTO.setBloodgroup(employee.getBloodgroup());
	                empDTO.setBmi(employee.getBmi());
	                empDTO.setHobbies(employee.getHobbies());
	                empDTO.setDepartment(employee.getDepartment());
	                return empDTO;
	            }).collect(Collectors.toList());

	    dto.setEnrolledEmployees(employeeDTOs);

	    return dto;
	}

	
}
