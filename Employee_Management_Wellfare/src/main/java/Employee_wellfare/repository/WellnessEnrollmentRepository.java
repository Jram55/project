package Employee_wellfare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Employee_wellfare.entity.WellnessEnrollment;

@Repository
public interface WellnessEnrollmentRepository extends JpaRepository<WellnessEnrollment, Long>{

	List<WellnessEnrollment> findByEmployeeEmployeeId(Long employeeId);

}
