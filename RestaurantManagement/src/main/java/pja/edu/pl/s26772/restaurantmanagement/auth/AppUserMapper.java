package pja.edu.pl.s26772.restaurantmanagement.auth;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserMapper {
    private final UserRoleRepository userRoleRepository;

    public AppUserMapper(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public AppUser requestDtoToAppUser(AppUserRequestDto requestDto){
        AppUser appUser = new AppUser();
        appUser.setEmail(requestDto.getEmail());
        appUser.setFirstName(requestDto.getFirstName());
        appUser.setLastName(requestDto.getLastName());
        UserRole userRole = userRoleRepository.findByName("USER").orElseThrow();
        appUser.getRoles().add(userRole);
        return appUser;
    }

    public AppUserResponseDto AppUserToResponseDto(AppUser user){
        AppUserResponseDto responseDto = new AppUserResponseDto();
        responseDto.setEmail(user.getEmail());
        responseDto.setPassword(user.getPassword());
        responseDto.setRoles(user.getRoles().stream().map(UserRole::getName).collect(Collectors.toSet()));
        return responseDto;
    }

}
