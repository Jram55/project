package Employee_wellfare.dto;

import java.time.LocalDate;

import java.util.List;

import Employee_wellfare.entity.WellnessProgramstatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WellnessProgramDTO {
    private long programID;
    private String wellnessProgramName;
    private LocalDate wellnessProgramStartDate;
    private LocalDate wellnessProgramEndDate;
    private String category;
    private String description;
    private String instructorName;
    private WellnessProgramstatus wellnessProgramStatus;
    private List<EmployeeDTO> enrolledEmployees;
    
    
}
