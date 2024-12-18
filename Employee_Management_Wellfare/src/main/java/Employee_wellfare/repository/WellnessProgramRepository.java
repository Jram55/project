package Employee_wellfare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Employee_wellfare.entity.WellnessProgram;

@Repository
public interface WellnessProgramRepository extends JpaRepository<WellnessProgram, Long>{

}
