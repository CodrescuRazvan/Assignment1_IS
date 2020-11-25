package repository.client;

import model.Account;
import model.Client;
import repository.EntityNotFoundException;

import java.util.List;

public interface ClientRepository {

    List<Client> findAll();

    Client findByPNC(Long PNC) throws EntityNotFoundException;

    boolean saveClient(Client client);

    boolean updateClient(Long PNC, String name, String cardNumber, String address, Account client);

    void removeAll();



}