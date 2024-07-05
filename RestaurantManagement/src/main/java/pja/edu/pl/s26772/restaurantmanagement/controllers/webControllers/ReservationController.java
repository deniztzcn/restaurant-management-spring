package pja.edu.pl.s26772.restaurantmanagement.controllers.webControllers;

import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pja.edu.pl.s26772.restaurantmanagement.auth.AppUser;
import pja.edu.pl.s26772.restaurantmanagement.auth.UserService;
import pja.edu.pl.s26772.restaurantmanagement.models.Reservation;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.ReservationRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.services.ReservationService;

import java.util.NoSuchElementException;

@Controller
public class ReservationController {
    private final ReservationService reservationService;
    private final UserService userService;

    public ReservationController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @GetMapping("/reservations")
    public String getReservations(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        AppUser appUser = userService.findByEmail(username).get();
        Long customerId = appUser.getCustomer().getId();
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto();
        reservationRequestDto.setCustomerId(customerId);
        model.addAttribute("reservationDto", reservationRequestDto);
        model.addAttribute("reservations", reservationService.getReservationsByCustomerId(customerId));
        return "reservations";
    }

    @PostMapping("/bookReservation")
    public String bookReservation(@Valid @ModelAttribute("reservationDto") ReservationRequestDto requestDto, BindingResult bindingResult){
        System.out.println(requestDto.getCustomerId());
        System.out.println(requestDto.getTableId());
        if(bindingResult.hasErrors()){
            return "reservations";
        }

        reservationService.addReservation(requestDto);
        return "redirect:/reservations";
    }

    @PostMapping("/reservations/delete/{id}")
    public String deleteReservation(@PathVariable Long id){
        try {
            Reservation reservation = reservationService.getReservation(id).orElseThrow();
            reservationService.deleteReservation(reservation);
        } catch (NoSuchElementException e) {
            return "redirect:/failedReservationDelete";
        }
        return "redirect:/reservationDeleted";
    }

    @GetMapping("/reservationNotFound")
    public String reservationNotFound(){
        return "reservationNotFound";
    }

    @GetMapping("/reservationDeleted")
    public String reservationDeleted(){
        return "reservationDeleted";
    }
}
