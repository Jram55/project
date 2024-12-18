package Employee_wellfare.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "emp")
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "employeeId")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
     
	//@NotNull(message="user name not to be null")
	private String name;

	private String password;

	private String role;

   // @Pattern(regexp = "^\\\\d{10}$", message = "contact number must be a 10-digit number")
	private String contactNumber;
    
    //@Email(message="enter the valid Email")
	private String email;

	private String age;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String bloodgroup;

	private double bmi;

	private String hobbies;

	private String department;
   
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<WellnessEnrollment> wellnessEnrollments;

	
	@OneToMany(mappedBy="employee",fetch=FetchType.LAZY)
	private List<ChallengeParticipation> challengeparipation;
	
	
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EventRegistration> eventRegistrations;

}