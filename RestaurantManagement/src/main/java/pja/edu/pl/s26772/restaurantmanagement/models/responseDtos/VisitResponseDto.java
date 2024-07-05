package pja.edu.pl.s26772.restaurantmanagement.models.responseDtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class VisitResponseDto {
    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<OrderResponseDto> orders;
    private Long tableId;
    private double costOfVisit;

    public VisitResponseDto() {
    }

    public double getCostOfVisit() {
        return costOfVisit;
    }

    public void setCostOfVisit(double costOfVisit) {
        this.costOfVisit = costOfVisit;
    }

    public List<OrderResponseDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponseDto> orders) {
        this.orders = orders;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
