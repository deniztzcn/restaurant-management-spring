package pja.edu.pl.s26772.restaurantmanagement.auth;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public class AppUserResponseDto {
    private String email;
    private String password;
    private Set<String> roles;

    public AppUserResponseDto() {
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

}
