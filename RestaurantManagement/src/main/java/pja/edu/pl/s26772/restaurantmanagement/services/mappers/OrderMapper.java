package pja.edu.pl.s26772.restaurantmanagement.services.mappers;

import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Employee;
import pja.edu.pl.s26772.restaurantmanagement.models.Orders;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.OrderRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.MenuItemResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.OrderResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.services.EmployeeService;

import java.time.LocalDateTime;

@Service
public class OrderMapper {
    private final EmployeeService employeeService;
    private final MenuItemMapper menuItemMapper;

    public OrderMapper(EmployeeService employeeService, MenuItemMapper menuItemMapper) {
        this.menuItemMapper = menuItemMapper;
        this.employeeService = employeeService;
    }

    public OrderResponseDto orderToResponseDto(Orders order){
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setOrderedTime(order.getOrderedTime());
        responseDto.setDeliveredTime(order.getDeliveredTime());
        MenuItemResponseDto itemResponseDto = menuItemMapper.menuItemToResponseDto(order.getId().getMenuItem());
        responseDto.setMenuItem(itemResponseDto);
        responseDto.setQuantity(order.getQuantity());
        responseDto.setTableId(order.getId().getVisit().getTables().getId());
        return responseDto;
    }

    public Orders requestToOrders(OrderRequestDto requestDto){
        Orders order = new Orders();
        order.setOrderedTime(LocalDateTime.now());
        Employee employee = employeeService.getEmployee(requestDto.getEmployeeId()).get();
        order.setEmployee(employee);
        order.setQuantity(requestDto.getQuantity());
        order.setDeliveredTime(null);
        return order;
    }

    public OrderRequestDto orderToRequestDto(Orders order) {
        OrderRequestDto requestDto = new OrderRequestDto();
        requestDto.setEmployeeId(order.getEmployee().getId());
        requestDto.setQuantity(order.getQuantity());
        requestDto.setMenuItemId(order.getId().getMenuItem().getId());
        requestDto.setVisitId(order.getId().getVisit().getId());
        return requestDto;
    }
}
