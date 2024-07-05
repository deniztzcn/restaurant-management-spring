package pja.edu.pl.s26772.restaurantmanagement.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Orders {
    @EmbeddedId
    private OrderPK id;
    private LocalDateTime orderedTime;
    private LocalDateTime deliveredTime;
    private int quantity;
    @ManyToOne
    private Employee employee;

    public Orders() {
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalDateTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public LocalDateTime getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(LocalDateTime deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public OrderPK getId() {
        return id;
    }

    public void setId(OrderPK id) {
        this.id = id;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Embeddable
    public static class OrderPK implements Serializable {
        @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
        private Visit visit;
        @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
        private MenuItem menuItem;

        public OrderPK() {
        }

        public Visit getVisit() {
            return visit;
        }

        public void setVisit(Visit visit) {
            this.visit = visit;
        }

        public MenuItem getMenuItem() {
            return menuItem;
        }

        public void setMenuItem(MenuItem menuItem) {
            this.menuItem = menuItem;
        }
    }
}
