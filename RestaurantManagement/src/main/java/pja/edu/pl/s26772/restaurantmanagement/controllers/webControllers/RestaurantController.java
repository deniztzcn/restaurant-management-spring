package pja.edu.pl.s26772.restaurantmanagement.controllers.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pja.edu.pl.s26772.restaurantmanagement.services.CategoryService;

@Controller
public class RestaurantController {
    private final CategoryService categoryService;

    public RestaurantController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/homePage")
    public String homePage(){
        return "homePage";
    }

    @GetMapping("/menu")
    public String getMenu(Model model){
        model.addAttribute("categories", categoryService.getCategoriesWithMenuItems());
        return "menu";
    }
}
