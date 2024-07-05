package pja.edu.pl.s26772.restaurantmanagement.auth;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pja.edu.pl.s26772.restaurantmanagement.auth.AppUser;

import java.util.Optional;

public interface UserRepository extends CrudRepository<AppUser,Long> {
    @Query("SELECT au FROM AppUser au WHERE au.email = :email")
    Optional<AppUser> findByEmail(@Param(("email")) String email);
}
