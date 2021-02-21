package dto;

import lombok.Data;
import model.Dish;

@Data
public class DishDto {

    private String name;

    private long price;


    private long timeCook;

    public Dish toDish() {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setPrice(price);
        dish.setTimeCook(timeCook);
        return dish;
    }

    public static DishDto fromDish(Dish dish) {
        DishDto dishDto = new DishDto();
        dishDto.setName(dish.getName());
        dishDto.setPrice(dish.getPrice());
        dishDto.setTimeCook(dish.getTimeCook());
        return dishDto;
    }

}
