package repository;

import database.DBConnectionFactory;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryCacheDecorator;
import repository.client.ClientRepositoryMySQL;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientRepositoryMySQLTest {

    private static ClientRepository repository;

    @BeforeClass
    public static void setupClass() {
        repository = new ClientRepositoryCacheDecorator(
                new ClientRepositoryMySQL(
                        new DBConnectionFactory().getConnectionWrapper(true).getConnection()
                ),
                new Cache<>()
        );
    }

    @Before
    public void cleanUp() {
        repository.removeAll();
    }

    @Test
    public void findAll() throws Exception {
        List<Client> clients = repository.findAll();
        assertEquals(clients.size(), 0);
    }

    @Test
    public void findAllWhenDbNotEmpty() throws Exception {
        Client client = new ClientBuilder()
                .setName("Name")
                .setCardNumber("CardNumber")
                .setAddress("Address")
                .build();
        repository.saveClient(client);
        repository.saveClient(client);
        repository.saveClient(client);

        List<Client> clients = repository.findAll();
        assertEquals(clients.size(), 3);
    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void save() throws Exception {
        assertTrue(repository.saveClient(
                new ClientBuilder()
                        .setName("Name")
                        .setCardNumber("CardNumber")
                        .setAddress("Address")
                        .build()
        ));
    }

    @Test
    public void removeAll() throws Exception {

    }
}
