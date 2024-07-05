package pja.edu.pl.s26772.restaurantmanagement.models.requestDtos;

import jakarta.validation.constraints.NotEmpty;

public class CustomerRequestDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;

    @NotEmpty
    private String address;

    @NotEmpty
    private Long phoneNumber;

    public CustomerRequestDto() {
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
