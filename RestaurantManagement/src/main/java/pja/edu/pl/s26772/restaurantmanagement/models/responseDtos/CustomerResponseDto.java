package pja.edu.pl.s26772.restaurantmanagement.models.responseDtos;

import java.util.List;

public class CustomerResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String address;
    private Long phoneNumber;
    private List<VisitResponseDto> visits;
    private List<ReservationResponseDto> reservations;

    public CustomerResponseDto() {
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<VisitResponseDto> getVisits() {
        return visits;
    }

    public void setVisits(List<VisitResponseDto> visits) {
        this.visits = visits;
    }

    public List<ReservationResponseDto> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationResponseDto> reservations) {
        this.reservations = reservations;
    }
}
