package Employee_wellfare.entity;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_registrations")
public class EventRegistration {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventRegistrationId;

    @ManyToOne(fetch=FetchType.EAGER) 
    @JoinColumn(name = "employee_id")
    private Employee employee; // FK to Employee

    @ManyToOne(fetch=FetchType.EAGER) 
    @JoinColumn(name = "event_id")
    private Event event; // FK to Event


 
    private Date eventRegistrationDate;


    private Date eventUnregistrationDate;
    
}




