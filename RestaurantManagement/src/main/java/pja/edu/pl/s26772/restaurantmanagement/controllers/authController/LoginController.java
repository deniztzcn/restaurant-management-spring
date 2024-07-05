package pja.edu.pl.s26772.restaurantmanagement.controllers.authController;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pja.edu.pl.s26772.restaurantmanagement.auth.AppUser;
import pja.edu.pl.s26772.restaurantmanagement.auth.UserService;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    String login() {
        return "login-form";
    }

    @GetMapping("/login-confirm")
    public String loginConfirm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        AppUser appUser = userService.findByEmail(userDetails.getUsername()).get();
        model.addAttribute("name", appUser.getFirstName() + " " + appUser.getLastName());
        return "login-confirm";
    }
}
