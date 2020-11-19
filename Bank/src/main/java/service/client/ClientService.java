package service.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client findByPNC(Long PNC) throws EntityNotFoundException;

    boolean save(Client client);

}