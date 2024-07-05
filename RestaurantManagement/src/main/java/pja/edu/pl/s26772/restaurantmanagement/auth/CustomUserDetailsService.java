package pja.edu.pl.s26772.restaurantmanagement.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
        logger.debug("CustomUserDetailsService initialized with UserService: {}", userService);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("loadUserByUsername called with username: {}", username);

        return userService.findUserCredentialsByEmail(username)
                .map(dto -> User.builder()
                        .username(dto.getEmail())
                        .password(dto.getPassword())
                        .roles(dto.getRoles().toArray(String[]::new))
                        .build())
                .orElseThrow(() ->{
                    logger.debug("User {} not found", username);
                    return new UsernameNotFoundException("User " + username + " not found");
                });
    }
}
