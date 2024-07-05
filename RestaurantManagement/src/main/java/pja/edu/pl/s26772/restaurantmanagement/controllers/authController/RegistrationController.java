package pja.edu.pl.s26772.restaurantmanagement.controllers.authController;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pja.edu.pl.s26772.restaurantmanagement.auth.AppUserRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.auth.UserService;
import pja.edu.pl.s26772.restaurantmanagement.models.Customer;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.CustomerRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.services.CustomerService;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final CustomerService customerService;

    public RegistrationController(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new AppUserRequestDto());
        return "registration-form";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") AppUserRequestDto user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registration-form";
        }
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setName(user.getFirstName());
        customerRequestDto.setSurname(user.getLastName());
        customerRequestDto.setAddress(user.getAddress());
        customerRequestDto.setPhoneNumber(user.getPhoneNumber());
        Customer customer = customerService.addCustomer(customerRequestDto);
        userService.register(user,customer);
        return "redirect:/confirm";
    }

    @GetMapping("/confirm")
    public String confirm(){
        return "registration-confirm";
    }
}
