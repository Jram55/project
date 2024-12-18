package Employee_wellfare.dto;

import java.util.List;

import Employee_wellfare.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    public EmployeeDTO(Long employeeId2, String name2, String role2, Long contactNumber2, String email2, String age2,
			Gender gender2, String bloodgroup2, double bmi2, String hobbies2, String department2) {
		
	}
	private Long employeeId;
    private String name;
    private String password;
    private String role;
    private String contactNumber;
    private String email;
    private String age;
    private Gender gender;
    private String bloodgroup;
    private double bmi;
    private String hobbies;
    private String department;
    
    private List<EnrollmentRequest> wellnessEnrollments;
    
    private List<ChallengeParticipationRequest> challengeparipation;
   
    private List<EventRegistrationRequest> eventRegistrations;
    // Getters and Setters
}
