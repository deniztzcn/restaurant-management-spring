package pja.edu.pl.s26772.restaurantmanagement.models.requestDtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints.CustomerExists;
import pja.edu.pl.s26772.restaurantmanagement.constraints.existsConstraints.TableExists;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class VisitRequestDto {
    @PastOrPresent
    private LocalDate date;
    @PastOrPresent
    private LocalTime startAt;
    @FutureOrPresent
    private LocalTime finishAt;

    @CustomerExists
    private Long customerId;

    @TableExists
    private Long tableId;

    public VisitRequestDto() {
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
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

    public LocalTime getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(LocalTime finishAt) {
        this.finishAt = finishAt;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
