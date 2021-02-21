package dto;

import model.Client;
import model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpringSecurityPersonFactory {

    public SpringSecurityPersonFactory() {
    }

    public static SpringSecurityPerson create(Client client) {
        return new SpringSecurityPerson(client.getId(), client.getName(), client.getAge(),
                client.getEmail(), client.getPassword(), mapToGrantedAuthorities(new ArrayList<>(client.getRoles())));
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
