package pja.edu.pl.s26772.restaurantmanagement.models.requestDtos;

import jakarta.validation.constraints.*;
import pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints.CustomerExists;
import pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints.TableExists;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationRequestDto {

    @FutureOrPresent
    private LocalDate date;
    @Future
    private LocalTime startAt;
    @CustomerExists
    private Long customerId;
    @TableExists
    private Long tableId;

    public ReservationRequestDto() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalTime startAt) {
        this.startAt = startAt;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
}
