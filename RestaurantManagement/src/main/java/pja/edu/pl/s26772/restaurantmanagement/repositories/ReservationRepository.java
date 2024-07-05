package pja.edu.pl.s26772.restaurantmanagement.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pja.edu.pl.s26772.restaurantmanagement.models.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Long> {
    @Query("SELECT r FROM Reservation r WHERE r.date = CURRENT_DATE")
    List<Reservation> getCurrentReservations();

    @Query("SELECT r FROM Reservation r WHERE r.date = :date")
    List<Reservation> getReservationByDate(@Param("date") LocalDate date);

    @Query("SELECT r FROM Reservation r WHERE r.customer.id = :customerId AND r.date >= CURRENT_DATE")
    List<Reservation> getReservationByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT r FROM Reservation r WHERE r.date = :date AND r.tables.id = :tableId AND r.startAt = :startAt")
    Optional<Reservation> findByDateAndTableAndStartAt(
            @Param("date") LocalDate date,
            @Param("tableId") Long tableId,
            @Param("startAt") LocalTime startAt);

    @Query("DELETE Reservation r WHERE r.id = :reservationId")
    @Transactional
    @Modifying
    void deleteReservation(@Param("reservationId") Long id);
}
