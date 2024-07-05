package pja.edu.pl.s26772.restaurantmanagement.services.mappers;

import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Customer;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.CustomerRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.CustomerResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.ReservationResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.VisitResponseDto;

import java.util.List;

@Service
public class CustomerMapper {
    private final VisitMapper visitMapper;
    private final ReservationMapper reservationMapper;

    public CustomerMapper(VisitMapper visitMapper, ReservationMapper reservationMapper) {
        this.visitMapper = visitMapper;
        this.reservationMapper = reservationMapper;
    }

    public CustomerResponseDto customerToResponseDto(Customer customer){
        CustomerResponseDto responseDto = new CustomerResponseDto();
        responseDto.setId(customer.getId());
        responseDto.setName(customer.getName());
        responseDto.setSurname(customer.getSurname());
        responseDto.setAddress(customer.getAddress());
        responseDto.setPhoneNumber(customer.getPhoneNumber());

        List<VisitResponseDto> visitDtos = customer.getVisits().stream()
                .map(visitMapper::visitToResponseDto)
                .toList();
        responseDto.setVisits(visitDtos);

        List<ReservationResponseDto> reservationDtos = customer.getReservations().stream()
                .map(reservationMapper::reservationToResponseDto)
                .toList();
        responseDto.setReservations(reservationDtos);

        return responseDto;
    }

    public Customer requestDtoToCustomer(CustomerRequestDto requestDto){
        Customer customer = new Customer();
        customer.setName(requestDto.getName());
        customer.setSurname(requestDto.getSurname());
        customer.setAddress(requestDto.getAddress());
        customer.setPhoneNumber(requestDto.getPhoneNumber());
        return customer;
    }

    public CustomerRequestDto customerToRequestDto(Customer customer){
        CustomerRequestDto requestDto = new CustomerRequestDto();
        requestDto.setName(customer.getName());
        requestDto.setSurname(customer.getSurname());
        requestDto.setAddress(customer.getAddress());
        requestDto.setPhoneNumber(customer.getPhoneNumber());
        return requestDto;
    }
}
