package Employee_wellfare.dto;

import java.sql.Date;
import java.util.List;

import Employee_wellfare.entity.EventStatus;
import Employee_wellfare.entity.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EventDto {

	private Long eventId;

	private String eventName;

	private String description;

	private EventType eventType; // Enum {Online, Offline}

	private Date eventDate;

	private String hostName;

	private EventStatus eventStatus; // Enum {Finished, Ongoing, Upcoming}

	private List<EmployeeDTO> eventregister;
}
