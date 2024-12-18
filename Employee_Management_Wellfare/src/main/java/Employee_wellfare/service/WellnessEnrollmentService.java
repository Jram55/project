package Employee_wellfare.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Employee_wellfare.entity.Employee;
import Employee_wellfare.entity.WellnessEnrollment;
import Employee_wellfare.entity.WellnessEnrollmentStatus;
import Employee_wellfare.entity.WellnessProgram;
import Employee_wellfare.repository.Employeerepository;
import Employee_wellfare.repository.WellnessEnrollmentRepository;
import Employee_wellfare.repository.WellnessProgramRepository;

@Service
public class WellnessEnrollmentService {

	@Autowired
	private Employeerepository employeeRepository;

	@Autowired
	private WellnessProgramRepository wellnessProgramRepository;

	@Autowired
	private WellnessEnrollmentRepository wellnessEnrollmentRepository;

	public WellnessEnrollment enrollEmployeeInProgram(Long employeeId, Long programID) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found"));
		WellnessProgram wellnessProgram = wellnessProgramRepository.findById(programID)
				.orElseThrow(() -> new RuntimeException("Wellness Program not found"));
		WellnessEnrollment enrollment = new WellnessEnrollment();
		enrollment.setEmployee(employee);
		enrollment.setWellnessProgram(wellnessProgram);
		enrollment.setEnrollDate(LocalDateTime.now());
		enrollment.setWellnessEnrollmentStatus(WellnessEnrollmentStatus.ENROLLED);

		return wellnessEnrollmentRepository.save(enrollment);
	}

	public List<Map<String, Object>> getEnrollmentsByEmployeeId(Long employeeId) {
		List<WellnessEnrollment> enrollments = wellnessEnrollmentRepository.findByEmployeeEmployeeId(employeeId);

		// Filter out unnecessary fields and return only employee and wellnessProgram
		return enrollments.stream().map(enrollment -> {
			Map<String, Object> filteredResult = new HashMap<>();

			// Add only the employee details
			Map<String, Object> employeeDetails = new HashMap<>();
			employeeDetails.put("employeeId", enrollment.getEmployee().getEmployeeId());
			employeeDetails.put("name", enrollment.getEmployee().getName());
			employeeDetails.put("password", enrollment.getEmployee().getPassword());
			employeeDetails.put("role", enrollment.getEmployee().getRole());
			employeeDetails.put("contactnumber", enrollment.getEmployee().getContactNumber());
			employeeDetails.put("email", enrollment.getEmployee().getEmail());
			employeeDetails.put("age", enrollment.getEmployee().getAge());
			employeeDetails.put("gender", enrollment.getEmployee().getGender());
			employeeDetails.put("bloodgroup", enrollment.getEmployee().getBloodgroup());
			employeeDetails.put("bmi", enrollment.getEmployee().getBmi());
			employeeDetails.put("hobbies", enrollment.getEmployee().getHobbies());
			employeeDetails.put("department", enrollment.getEmployee().getDepartment());
			filteredResult.put("employee", employeeDetails);

			// Add only the wellness program details
			Map<String, Object> wellnessProgramDetails = new HashMap<>();
			wellnessProgramDetails.put("programID", enrollment.getWellnessProgram().getProgramID());
			wellnessProgramDetails.put("programName", enrollment.getWellnessProgram().getWellnessProgramName());
			wellnessProgramDetails.put("startdate", enrollment.getWellnessProgram().getWellnessProgramStartDate());
			wellnessProgramDetails.put("enddate", enrollment.getWellnessProgram().getWellnessProgramEndDate());
			wellnessProgramDetails.put("category", enrollment.getWellnessProgram().getCategory());
			wellnessProgramDetails.put("description", enrollment.getWellnessProgram().getDescription());
			wellnessProgramDetails.put("InstructorName", enrollment.getWellnessProgram().getInstructorName());
			wellnessProgramDetails.put("programstatus", enrollment.getWellnessProgram().getWellnessProgramStatus());
			filteredResult.put("wellnessProgram", wellnessProgramDetails);

			return filteredResult;
		}).collect(Collectors.toList());
	}

	public void deleteWellnessprogram(Long programID) {
		wellnessEnrollmentRepository.deleteById(programID);

		;
	}
}
