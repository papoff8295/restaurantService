package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "dishes")
public class Dish extends AbstractBaseEntity{

    @NotEmpty
    private String name;

    @NotNull
    private long price;

    @NotNull
    private long timeCook;

    //a dish can have one restaurant
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;
 }
