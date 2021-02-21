package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "restourants")
//this class if many restaurants
class Restaurant extends AbstractBaseEntity{

    @NotEmpty
    private String name;

    @OneToMany(mappedBy="owner")
    private List<Dish> dishes;



}
