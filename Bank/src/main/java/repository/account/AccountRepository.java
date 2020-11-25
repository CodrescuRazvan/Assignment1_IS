package repository.account;

import model.Client;
import model.Account;
import repository.EntityNotFoundException;

import java.io.IOException;
import java.util.List;

public interface AccountRepository {

    List<Account> findAll();

    Account findById(Long id) throws EntityNotFoundException;

    boolean saveAccount(Account account);

    boolean deleteAccount(Long id);

    boolean updateAccount(Account account);

    void removeAll();

    boolean transferMoney(Account account1, Account account2, Long money);

    boolean generateBill(Account account, Long water, Long gas, Long electricity) throws IOException;
}
