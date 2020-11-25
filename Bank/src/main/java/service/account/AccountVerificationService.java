package service.account;

import model.Account;
import model.validation.Notification;
import repository.EntityNotFoundException;

public interface AccountVerificationService {

    Notification<Boolean> saveAccount(String type, Long money);

    Notification<Boolean> deleteAccount(Long id) throws EntityNotFoundException;

    Notification<Boolean> updateAccount(Account account) throws EntityNotFoundException;

    Notification<Boolean> transferMoney(Account account1, Account account2, Long money);
}
