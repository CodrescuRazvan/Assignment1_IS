package repository.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRepositoryMock implements ClientRepository {

    private List<Client> clients;

    public ClientRepositoryMock() {
        clients = new ArrayList<>();
    }

    public List<Client> findAll() {
        return clients;
    }

    public Client findByPNC(Long PNC) throws EntityNotFoundException {
        List<Client> filteredClients = clients.parallelStream()
                .filter(it -> it.getPNC().toString().equals(PNC.toString()))
                .collect(Collectors.toList());
        if (filteredClients.size() > 0) {
            return filteredClients.get(0);
        }
        throw new EntityNotFoundException(PNC, Client.class.getSimpleName());
    }

    public boolean saveClient(Client client) {
        return clients.add(client);
    }

    @Override
    public void updateClient(Client client) {

    }

    @Override
    public void removeAll() {
        clients.clear();
    }
}