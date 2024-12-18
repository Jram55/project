package Employee_wellfare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Employee_wellfare.entity.HealthcareProvider;

@Repository
public interface HealthcareProviderRepository extends JpaRepository<HealthcareProvider, Long>{

}
