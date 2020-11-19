package repository.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.List;

public interface ClientRepository {

    List<Client> findAll();

    Client findByPNC(Long PNC) throws EntityNotFoundException;

    boolean saveClient(Client client);

    void updateClient(Client client);

    void removeAll();



}