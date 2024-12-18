package Employee_wellfare.entity;


import java.time.LocalDate;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "challenges")

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "challengeId")

public class Challenge {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeId;


   // @NotBlank(message = "Challenge name is mandatory")
    private String challengeName;


    private String description;


    @Enumerated(EnumType.STRING)
    private ChallengeType challengeType; // Enum{Step challenge,Cycling,Healthy eating,Marathon}


   // @NotNull(message = "Challenge start date is mandatory")
    private LocalDate challengeStartDate;


    //@NotNull(message = "Challenge end date is mandatory")
    private LocalDate challengeEndDate;


    private Integer score;
    

    
    @OneToMany(mappedBy = "challenge",fetch=FetchType.LAZY,cascade=CascadeType.ALL) 
    private List<ChallengeParticipation> challengeParticipations;



  
}






