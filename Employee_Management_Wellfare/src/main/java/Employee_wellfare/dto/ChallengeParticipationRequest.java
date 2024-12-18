package Employee_wellfare.dto;

import java.time.LocalDateTime;

import Employee_wellfare.entity.Progress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChallengeParticipationRequest {
			
		private long employeeId ;
		private long challengeId ;
		
		private Long participationId;
		
		private LocalDateTime participationDate;
		private Progress progress;
		private ChallengeDto challenge;
	}

