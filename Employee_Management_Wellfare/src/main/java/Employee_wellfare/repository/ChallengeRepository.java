package Employee_wellfare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Employee_wellfare.entity.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

}
