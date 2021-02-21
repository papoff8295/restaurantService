package model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class Client extends AbstractBaseEntity{

    @NotEmpty(message = "Name should not empty!")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @NotEmpty(message = "Email should be empty!")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty
    @Size(min = 5, message = "Password should be between 2 char")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_roles",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "client_orders",
    joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private List<Order> orders;

}
