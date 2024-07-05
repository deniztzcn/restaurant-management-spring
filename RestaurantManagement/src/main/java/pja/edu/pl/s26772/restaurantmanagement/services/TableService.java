package pja.edu.pl.s26772.restaurantmanagement.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Customer;
import pja.edu.pl.s26772.restaurantmanagement.models.Tables;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.CustomerResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.OrderResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.TableResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.OrderRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.TableRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TableService {
    private final TableRepository tableRepository;
    private final OrderService orderService;
    private final CustomerService customerService;

    public TableService(TableRepository tableRepository, OrderService orderService, CustomerService customerService) {
        this.tableRepository = tableRepository;
        this.orderService = orderService;
        this.customerService = customerService;
    }


    public void deleteTable(Tables tables){
        tableRepository.delete(tables);
    }

    public Optional<Tables> getTable(Long id){
        return tableRepository.findById(id);
    }

    public List<TableResponseDto> getTables(){
        List<Tables> tables = tableRepository.getAllTables();
        List<TableResponseDto> responseDtos = new ArrayList<>();
        for(Tables table : tables){
            TableResponseDto responseDto = getTableById(table.getId());
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }

    public TableResponseDto getTableById(Long tableId){
        Tables table = tableRepository.findById(tableId).get();
        TableResponseDto responseDto = new TableResponseDto();
        List<OrderResponseDto> orders = orderService.getOrdersForGivenTable(table.getId());
        Customer customer = tableRepository.getCurrentCustomer(table.getId()).get();
        CustomerResponseDto customerResponseDto = customerService.getResponseDto(customer);
        responseDto.setTableId(table.getId());
        responseDto.setCustomer(customerResponseDto);
        responseDto.setOrders(orders);
        return responseDto;
    }

    @Transactional
    public void addTable(){
        tableRepository.save(new Tables());
    }
}
