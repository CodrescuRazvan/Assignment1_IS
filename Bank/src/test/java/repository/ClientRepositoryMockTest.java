package repository;

import model.Client;
import model.builder.ClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryCacheDecorator;
import repository.client.ClientRepositoryMock;

import static org.junit.Assert.assertTrue;

public class ClientRepositoryMockTest {

    private static ClientRepository repository;

    @BeforeClass
    public static void setupClass() {
        repository = new ClientRepositoryCacheDecorator(
                new ClientRepositoryMock(),
                new Cache<>()
        );
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findAll() throws Exception {
        assertTrue(repository.findAll().size() == 0);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdEx() throws Exception {
        repository.findByPNC(1L);
    }

    @Test
    public void save() throws Exception {
        Client client = new ClientBuilder()
                .setPNC(1L)
                .setName("Name")
                .setCardNumber("CardNumber")
                .setAddress("Address")
                .build();

        assertTrue(repository.saveClient(client));
    }
}
