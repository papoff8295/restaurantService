package service;

import lombok.extern.slf4j.Slf4j;
import model.Client;
import model.Dish;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ClientRepository;
import repository.DishRepository;
import repository.OrderRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public RestaurantServiceImpl(DishRepository dishRepository, OrderRepository orderRepository, ClientRepository clientRepository) {
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Dish> getAllDishes(Long restId) {
        List<Dish> dishList = dishRepository.findAll();
        log.info("IN getAllDishes - {} dishList found", dishList.size());
        return dishList;
    }


    //If the client has already received a list of food, then it is better to count the time on the client's side
    @Override
    public Long getCookingTime(List<Dish> dishes, Long restId) {
        AtomicLong cookingTime = new AtomicLong();

        dishes.forEach(dish -> {
            cookingTime.set(dish.getTimeCook());
        });

        log.info("IN getCookingTime- {} cookingTime found", cookingTime.get());
        return cookingTime.get();
    }

    @Override
    public Order createOrder(List<Dish> dishes, Long restId, Long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            log.warn("IN findById - no client found by id: {}", clientId);
            return null;
        }
        Order order = new Order();
        order.setDate(new Date());
        order.setClient(client);
        order.setDishList(dishes);
        Order createdOrder = orderRepository.save(order);
        log.info("IN createOrder - order: {} successfully created", order);
        return createdOrder;
    }
}
