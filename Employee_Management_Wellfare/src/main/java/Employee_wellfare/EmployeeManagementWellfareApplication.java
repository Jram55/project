package Employee_wellfare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("Employee_wellfare.repository")
public class EmployeeManagementWellfareApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementWellfareApplication.class, args);
	}

}
