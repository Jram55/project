package Employee_wellfare.dto;

import java.time.LocalDateTime;

import Employee_wellfare.entity.WellnessEnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequest {
    private Long employeeId;
    private Long programID;
    
    private long enrollmentid;
    private LocalDateTime enrollDate;
    private WellnessEnrollmentStatus wellnessEnrollmentStatus;
    private WellnessProgramDTO wellnessProgram;
}
