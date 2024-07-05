package pja.edu.pl.s26772.restaurantmanagement.services;

import jakarta.transaction.Transactional;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Employee;
import pja.edu.pl.s26772.restaurantmanagement.models.MenuItem;
import pja.edu.pl.s26772.restaurantmanagement.models.Orders;
import pja.edu.pl.s26772.restaurantmanagement.models.Visit;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.OrderRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.OrderResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.EmployeeRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.MenuItemRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.OrderRepository;
import pja.edu.pl.s26772.restaurantmanagement.repositories.VisitRepository;
import pja.edu.pl.s26772.restaurantmanagement.services.mappers.OrderMapper;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final EmployeeRepository employeeRepository;
    private final MenuItemRepository menuItemRepository;
    private final VisitRepository visitRepository;
    private final OrderMapper orderMapper;

    public OrderService(VisitRepository visitRepository,MenuItemRepository menuItemRepository,EmployeeRepository employeeRepository,OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.employeeRepository = employeeRepository;
        this.menuItemRepository = menuItemRepository;
        this.visitRepository = visitRepository;
    }

    public List<OrderResponseDto> getUndeliveredOrders(){
        return orderRepository.findUndeliveredOrdersForCurrentVisits()
                .stream()
                .map(orderMapper::orderToResponseDto).toList();
    }

    public List<OrderResponseDto> getUndeliveredOrderForGivenTable(Long tableId){
        List<Orders> orders = orderRepository.getUndeliveredOrdersForGivenTable(tableId);
        Map<Long,OrderResponseDto> dtoMap = new HashMap<>();
        for(Orders order : orders){
            Long itemId = order.getId().getMenuItem().getId();
            OrderResponseDto responseDto = orderMapper.orderToResponseDto(order);

            if(dtoMap.containsKey(itemId)){
                OrderResponseDto currentDto = dtoMap.get(itemId);
                currentDto.setQuantity(currentDto.getQuantity() + responseDto.getQuantity());
            } else {
                dtoMap.put(itemId,responseDto);
            }
        }
        return new ArrayList<>(dtoMap.values());
    }
    @Transactional
    public OrderResponseDto addOrder(OrderRequestDto requestDto){
        return orderMapper.orderToResponseDto(orderRepository.save(orderMapper.requestToOrders(requestDto)));
    }

    public List<OrderResponseDto> getOrdersForGivenTable(Long tableId){
        List<Orders> orders = orderRepository.getOrdersByTableId(tableId);
        Map<Long,OrderResponseDto> dtoMap = new HashMap<>();
        for(Orders order : orders){
            Long itemId = order.getId().getMenuItem().getId();
            OrderResponseDto responseDto = orderMapper.orderToResponseDto(order);

            if(dtoMap.containsKey(itemId)){
                OrderResponseDto currentDto = dtoMap.get(itemId);
                currentDto.setQuantity(currentDto.getQuantity() + responseDto.getQuantity());
            } else {
                dtoMap.put(itemId,responseDto);
            }
        }
        return new ArrayList<>(dtoMap.values());
    }



    public Optional<Orders> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public OrderRequestDto getRequestDto(Orders order){
        return orderMapper.orderToRequestDto(order);
    }

    @Transactional
    public void deleteOrder(Orders order) {
        orderRepository.delete(order);
    }
    @Transactional
    public void updateOrder(Orders order, OrderRequestDto patchedDto) {
        order.setQuantity(patchedDto.getQuantity());
        Employee emp = employeeRepository.findById(patchedDto.getEmployeeId()).orElseThrow();
        order.setEmployee(emp);
        MenuItem item = menuItemRepository.findById(patchedDto.getEmployeeId()).orElseThrow();
        Visit visit = visitRepository.findById(patchedDto.getVisitId()).orElseThrow();
        Orders.OrderPK id = new Orders.OrderPK();
        id.setMenuItem(item);
        id.setVisit(visit);
        orderRepository.save(order);
    }
}
