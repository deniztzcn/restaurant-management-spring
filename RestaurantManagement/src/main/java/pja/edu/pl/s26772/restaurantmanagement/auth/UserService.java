package pja.edu.pl.s26772.restaurantmanagement.auth;//package pja.edu.pl.s26772.restaurantmanagement.services;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Customer;
import pja.edu.pl.s26772.restaurantmanagement.services.CustomerService;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AppUserMapper appUserMapper;

    public UserService(UserRepository userRepository, AppUserMapper appUserMapper) {
        this.userRepository = userRepository;
        this.appUserMapper = appUserMapper;
    }

    @Transactional
    public void register(AppUserRequestDto requestDto, Customer customer){
        AppUser appUser = appUserMapper.requestDtoToAppUser(requestDto);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        appUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        appUser.setCustomer(customer);
        userRepository.save(appUser);
    }

    public Optional<AppUserResponseDto> findUserCredentialsByEmail(String email) {
        return userRepository.findByEmail(email).map(appUserMapper::AppUserToResponseDto);
    }

    public Optional<AppUser> findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    public boolean isEmailExists(String s) {
        return userRepository.findByEmail(s).isPresent();
    }
}