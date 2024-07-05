package pja.edu.pl.s26772.restaurantmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pja.edu.pl.s26772.restaurantmanagement.models.Visit;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitRepository extends CrudRepository<Visit,Long> {
    @Query("SELECT v FROM Visit v")
    List<Visit> getAllVisits();

    @Query("SELECT v FROM Visit v WHERE v.startTime <= CURRENT_TIMESTAMP AND v.endTime IS NULL")
    List<Visit> findOngoingVisits();

    @Query("SELECT v FROM Visit v WHERE v.date = :date")
    List<Visit> getVisitsByDate(@Param("date") LocalDate date);
}
