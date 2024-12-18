package Employee_wellfare.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRegistrationRequest {

	private long employeeId;
	private long eventId;

	private Long eventRegistrationId;

	private Date eventRegistrationDate;

	private Date eventUnregistrationDate;
	private EventDto event; 
}
