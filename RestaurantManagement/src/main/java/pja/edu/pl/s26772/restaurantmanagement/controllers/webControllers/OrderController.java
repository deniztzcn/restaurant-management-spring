package pja.edu.pl.s26772.restaurantmanagement.controllers.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping("/orders")
    public String getOrdersByCustomerId(){
        return "homePage";
    }
}
