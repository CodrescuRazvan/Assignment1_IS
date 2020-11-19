package repository.client;

import model.Client;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.List;

public class ClientRepositoryCacheDecorator extends ClientRepositoryDecorator {

    private Cache<Client> cache;

    public ClientRepositoryCacheDecorator(ClientRepository clientRepository, Cache<Client> cache) {
        super(clientRepository);
        this.cache = cache;
    }

    @Override
    public List<Client> findAll() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<Client> clients = decoratedRepository.findAll();
        cache.save(clients);
        return clients;
    }

    @Override
    public Client findByPNC(Long PNC) throws EntityNotFoundException {
        return decoratedRepository.findByPNC(PNC);
    }

    @Override
    public boolean saveClient(Client client) {
        cache.invalidateCache();
        return decoratedRepository.saveClient(client);
    }

    @Override
    public void updateClient(Client client) {

    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
    }
}