package Employee_wellfare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Employee_wellfare.entity.ChallengeParticipation;

@Repository
public interface ChallengeParticipationRepository extends JpaRepository<ChallengeParticipation, Long>{

	//List<ChallengeParticipation> findByEmployeeId(Long employeeId);

	List<ChallengeParticipation> findByEmployeeEmployeeId(Long employeeId);

}
