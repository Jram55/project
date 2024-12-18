package Employee_wellfare.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
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

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "eventId")

public class Event {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;


  
    private String eventName;


    private String description;


    @Enumerated(EnumType.STRING)
    private EventType eventType; // Enum {Online, Offline}


    private Date eventDate;


    private String hostName;



    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus; // Enum {Finished, Ongoing, Upcoming}
    

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EventRegistration> eventregistration;
   
}
