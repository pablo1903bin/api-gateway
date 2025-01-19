package comapigateway.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comapigateway.entities.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
	List<Group> findByCreadoPor(Long userId);
}