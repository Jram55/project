package Employee_wellfare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Employee_wellfare.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
