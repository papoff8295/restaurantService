package controller;

import model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.RestaurantService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/dishes")
    public String getAllDishes(@PathVariable("restId") long restId, Model model) {
        model.addAttribute("dishes", restaurantService.getAllDishes(restId));
        return "dishes";
    }

    //If the client has already received a list of food, then it is better to count the time on the client's side
    @PostMapping("/dishes")
    public ResponseEntity getCookingTime(@RequestBody List<Dish> dishes, @PathVariable("restId") Long restId) {
        long cookingTime = restaurantService.getCookingTime(dishes, restId);
        Map<Object, Object> response = new HashMap<>();
        response.put("cookingTime", cookingTime);
        return ResponseEntity.ok(response);
    }
}
