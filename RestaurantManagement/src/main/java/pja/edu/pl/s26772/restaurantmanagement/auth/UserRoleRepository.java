package pja.edu.pl.s26772.restaurantmanagement.auth;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole,Long> {
    @Query("SELECT ur FROM UserRole ur WHERE ur.name = :name")
    Optional<UserRole> findByName(@Param("name") String name);
}
