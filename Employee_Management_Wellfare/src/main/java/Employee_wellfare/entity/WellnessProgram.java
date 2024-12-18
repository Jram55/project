package Employee_wellfare.entity;

import java.time.LocalDate;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "programID")

public class WellnessProgram {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long programID;

	private String wellnessProgramName;

	private LocalDate wellnessProgramStartDate;

	private LocalDate wellnessProgramEndDate;

	private String category;

	private String description;

	// @NotBlank(message = "Instructor name is mandatory")
	private String instructorName;

	@Enumerated(EnumType.STRING)
	private WellnessProgramstatus wellnessProgramStatus; // Enum {Completed, Pending, Ongoing}

	
	
	
	@OneToMany(mappedBy = "wellnessProgram", fetch = FetchType.LAZY)
	private List<WellnessEnrollment> wellnessEnrollments;

}
