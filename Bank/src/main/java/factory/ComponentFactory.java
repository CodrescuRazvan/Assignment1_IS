package factory;

import database.DBConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.admin.AdminRepository;
import repository.admin.AdminRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.account.AccountVerificationService;
import service.account.AccountVerificationServiceMySQL;
import service.client.ClientVerificationService;
import service.client.ClientVerificationServiceMySQL;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ComponentFactory {

    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final AdminRepository adminRepository;
    private final AccountVerificationService accountVerificationService;
    private final ClientVerificationService clientVerificationService;

    private static ComponentFactory instance;

    private List<String> statements;

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
        this.adminRepository = new AdminRepositoryMySQL(connection);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        this.accountVerificationService = new AccountVerificationServiceMySQL(this.accountRepository);
        this.clientVerificationService = new ClientVerificationServiceMySQL(this.clientRepository);
        this.statements = new ArrayList<>();
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

    public AdminRepository getAdminRepository() {
        return adminRepository;
    }

    public AccountVerificationService getAccountVerificationService() {
        return accountVerificationService;
    }

    public ClientVerificationService getClientVerificationService() {
        return clientVerificationService;
    }

    public List<String> getStatements() {
        return statements;
    }

    public void setStatements(List<String> statements){
        this.statements = statements;
    }
}