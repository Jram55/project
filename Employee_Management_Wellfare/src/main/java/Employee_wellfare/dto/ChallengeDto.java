package Employee_wellfare.dto;

import java.time.LocalDate;
import java.util.List;

import Employee_wellfare.entity.ChallengeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDto {

	private Long challengeId;

	private String challengeName;

	private String description;

	private ChallengeType challengeType; // Enum{Step challenge,Cycling,Healthy eating,Marathon}

	private LocalDate challengeStartDate;

	private LocalDate challengeEndDate;

	private Integer score;

	private List<EmployeeDTO> participationemployee;

}
