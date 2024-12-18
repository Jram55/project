package Employee_wellfare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Employee_wellfare.entity.Employee;


@Repository
public interface Employeerepository extends JpaRepository<Employee, Long>{

	//Optional<Employee> findByName(String name);

	Optional<Employee> findByEmail(String email);

	List<Employee> findByRole(String role);

	

}
