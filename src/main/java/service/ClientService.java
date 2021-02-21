package service;

import model.Client;

import java.util.List;

public interface ClientService {

    Client register(Client client);

    List<Client> getAll();

    Client findByUsername(String username);

    Client findById(Long id);

    void delete(Long id);


}
