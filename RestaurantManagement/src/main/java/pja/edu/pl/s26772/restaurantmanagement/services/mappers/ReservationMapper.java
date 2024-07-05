package pja.edu.pl.s26772.restaurantmanagement.services.mappers;

import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Customer;
import pja.edu.pl.s26772.restaurantmanagement.models.Reservation;
import pja.edu.pl.s26772.restaurantmanagement.models.Tables;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.ReservationRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.ReservationResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.CustomerRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.TableRepository;

@Service
public class ReservationMapper {
    private final CustomerRepository customerRepository;
    private final TableRepository tableRepository;

    public ReservationMapper(TableRepository tableRepository,CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.tableRepository = tableRepository;
    }

    public ReservationResponseDto reservationToResponseDto(Reservation reservation){
        ReservationResponseDto responseDto = new ReservationResponseDto();
        responseDto.setDate(reservation.getDate());
        responseDto.setId(reservation.getId());
        responseDto.setStartAt(reservation.getStartAt());
        responseDto.setTableId(reservation.getTable().getId());
        responseDto.setCustomerId(reservation.getCustomer().getId());
        return responseDto;
    }

    public Reservation requestDtoToReservation(ReservationRequestDto requestDto){
        Reservation reservation = new Reservation();
        Customer customer = customerRepository.findById(requestDto.getCustomerId()).get();
        reservation.setCustomer(customer);
        reservation.setDate(requestDto.getDate());
        reservation.setStartAt(requestDto.getStartAt());
        Tables table = tableRepository.findById(requestDto.getTableId()).get();
        reservation.setTable(table);
        return reservation;
    }

    public ReservationRequestDto reservationToRequestDto(Reservation reservation) {
        ReservationRequestDto requestDto = new ReservationRequestDto();
        requestDto.setDate(reservation.getDate());
        requestDto.setStartAt(reservation.getStartAt());
        requestDto.setCustomerId(reservation.getCustomer().getId());
        requestDto.setTableId(reservation.getTable().getId());
        return requestDto;
    }
}
