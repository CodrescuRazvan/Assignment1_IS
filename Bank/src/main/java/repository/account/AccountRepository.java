package repository.account;

import model.Client;
import model.Account;
import repository.EntityNotFoundException;

import java.util.List;

public interface AccountRepository {

    List<Account> findAll();

    Account findById(Long id) throws EntityNotFoundException;

    boolean saveAccount(Account account);

    void deleteAccount(Long id);

    void updateAccount(Account account);

    void removeAll();
}
