package pja.edu.pl.s26772.restaurantmanagement.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import pja.edu.pl.s26772.restaurantmanagement.constraints.passwordConstraints.*;
import pja.edu.pl.s26772.restaurantmanagement.constraints.uniqueConstraints.UniqueEmail;

public class AppUserRequestDto {
    private String firstName;
    private String lastName;

    @UniqueEmail
    @Email
    private String email;
    @HasDigit(numberOfDigits = 1)
    @HasLowercase(numberOfLowercase = 1)
    @HasUppercase(numberOfUppercase = 1)
    @HasSpecialCharacters(numberOfSpecialCharacters = 1)
    @PasswordSize(minSize = 7)
    private String password;
    private String address;

    @Positive
    private Long phoneNumber;

    public AppUserRequestDto() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
