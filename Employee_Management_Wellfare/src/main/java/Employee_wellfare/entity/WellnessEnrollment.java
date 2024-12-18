package Employee_wellfare.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WellnessEnrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long enrollmentid;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id",referencedColumnName = "employeeId")

	private Employee employee; // FK to Employee

	// @JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "wellness_program_id",referencedColumnName = "programID")
	private WellnessProgram wellnessProgram; // FK to WellnessProgram

	// @NotNull(message = "Enroll date is mandatory")
	private LocalDateTime enrollDate;

	@Enumerated(EnumType.STRING)
	private WellnessEnrollmentStatus wellnessEnrollmentStatus; // Enum(Enrolled,Unenrolled)

	

}
