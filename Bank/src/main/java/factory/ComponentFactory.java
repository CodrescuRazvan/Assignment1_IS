package factory;

import database.DBConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;

import java.sql.Connection;

public class ComponentFactory {

    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.accountRepository = new AccountRepositoryMySQL(connection);
        this.clientRepository = new ClientRepositoryMySQL(connection);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public ClientRepository getClientRepository(){
        return clientRepository;
    }
}