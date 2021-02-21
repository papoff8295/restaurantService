package service;

import model.Dish;
import model.Order;

import java.util.List;

public interface RestaurantService {
    //some methods
    //....
    // task 2, 3
    List<Dish> getAllDishes(Long restId);

    Long getCookingTime(List<Dish> dishes, Long restId);

    Order createOrder(List<Dish> dishes, Long restId, Long clientId);

}
