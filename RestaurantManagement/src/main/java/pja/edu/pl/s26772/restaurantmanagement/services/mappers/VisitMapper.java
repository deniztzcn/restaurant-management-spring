package pja.edu.pl.s26772.restaurantmanagement.services.mappers;

import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Customer;
import pja.edu.pl.s26772.restaurantmanagement.models.Visit;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.VisitRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.OrderResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.VisitResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.CustomerRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.TableRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.VisitRepository;
import pja.edu.pl.s26772.restaurantmanagement.services.CustomerService;
import pja.edu.pl.s26772.restaurantmanagement.services.TableService;

import java.util.List;

@Service
public class VisitMapper {
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final TableRepository tableRepository;

    public VisitMapper(OrderMapper orderMapper, CustomerRepository customerRepository, TableRepository tableRepository) {
        this.orderMapper = orderMapper;
        this.customerRepository = customerRepository;
        this.tableRepository = tableRepository;
    }

    public VisitRequestDto visitToRequestDto(Visit visit){
        VisitRequestDto requestDto = new VisitRequestDto();
        requestDto.setDate(visit.getDate());
        requestDto.setCustomerId(visit.getCustomer().getId());
        requestDto.setStartAt(visit.getStartTime());
        requestDto.setFinishAt(visit.getEndTime());
        requestDto.setTableId(visit.getTables().getId());
        return requestDto;
    }

    public Visit requestDtoToVisit(VisitRequestDto requestDto){
        Visit visit = new Visit();
        Customer customer = customerRepository.findById(requestDto.getCustomerId()).orElse(null);
        visit.setCustomer(customer);
        visit.setDate(requestDto.getDate());
        visit.setTables(tableRepository.findById(requestDto.getTableId()).get());
        visit.setStartTime(requestDto.getStartAt());
        visit.setEndTime(requestDto.getFinishAt());
        return visit;
    }

    public VisitResponseDto visitToResponseDto(Visit visit){
        VisitResponseDto responseDto = new VisitResponseDto();
        responseDto.setId(visit.getId());
        responseDto.setDate(visit.getDate());
        responseDto.setStartTime(visit.getStartTime());
        responseDto.setEndTime(visit.getEndTime());
        List<OrderResponseDto> responseDtos = visit.getOrders().stream().map(orderMapper::orderToResponseDto).toList();
        responseDto.setOrders(responseDtos);
        responseDto.setTableId(visit.getTables().getId());
        double cost = calculateCost(responseDtos);
        responseDto.setCostOfVisit(cost);
        return responseDto;
    }

    private double calculateCost(List<OrderResponseDto> orderResponseDtos){
        double cost = 0.0;
        for(OrderResponseDto order : orderResponseDtos){
            double price = order.getMenuItem().getPrice();
            cost += price * order.getQuantity();
        }
        return cost;
    }
}
