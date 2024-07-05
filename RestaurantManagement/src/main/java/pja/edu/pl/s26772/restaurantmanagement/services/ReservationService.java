package pja.edu.pl.s26772.restaurantmanagement.services;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Customer;
import pja.edu.pl.s26772.restaurantmanagement.models.Reservation;
import pja.edu.pl.s26772.restaurantmanagement.models.Tables;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.ReservationRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.ReservationResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.CustomerRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.ReservationRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.TableRepository;
import pja.edu.pl.s26772.restaurantmanagement.services.mappers.ReservationMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final TableRepository tableRepository;
    private final CustomerRepository customerRepository;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, TableRepository tableRepository, CustomerRepository customerRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.tableRepository = tableRepository;
        this.customerRepository = customerRepository;
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationResponseDto> getCurrentReservations() {
        List<Reservation> reservations = reservationRepository.getCurrentReservations();
        return reservations.stream().map(reservationMapper::reservationToResponseDto).toList();
    }

    public List<ReservationResponseDto> getReservationsByCustomerId(Long id){
        List<Reservation> reservations = reservationRepository.getReservationByCustomerId(id);
        return reservations.stream().map(reservationMapper::reservationToResponseDto).toList();
    }

    public List<ReservationResponseDto> getReservationsByDate(LocalDate date){
        List<Reservation> reservations = reservationRepository.getReservationByDate(date);
        return reservations.stream().map(reservationMapper::reservationToResponseDto).toList();
    }

    @Transactional
    public ReservationResponseDto addReservation(ReservationRequestDto requestDto) {
        return reservationMapper.reservationToResponseDto
                (reservationRepository.save(
                        reservationMapper.requestDtoToReservation(requestDto)));
    }

    public Optional<Reservation> getReservation(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }
    @Transactional
    @Modifying
    public void deleteReservation(Reservation reservation) {
        reservationRepository.deleteReservation(reservation.getId());
    }

    public ReservationRequestDto getRequestDto(Reservation reservation) {
        return reservationMapper.reservationToRequestDto(reservation);
    }

    @Transactional
    public void updateReservation(Reservation reservation, ReservationRequestDto patchedDto) {
        reservation.setDate(patchedDto.getDate());
        Tables table = tableRepository.findById(patchedDto.getTableId()).get();
        Customer customer = customerRepository.findById(patchedDto.getCustomerId()).get();
        reservation.setTable(table);
        reservation.setCustomer(customer);
        reservation.setDate(patchedDto.getDate());
        reservation.setStartAt(patchedDto.getStartAt());
        reservationRepository.save(reservation);
    }

    public Optional<Reservation> getReservationByDateTableAndTime(LocalDate date, Long tableId, LocalTime time){
        return reservationRepository.findByDateAndTableAndStartAt(date,tableId,time);
    }
}
