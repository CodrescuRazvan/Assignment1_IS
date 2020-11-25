package service.account;

import factory.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import model.validation.*;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;

import java.util.Date;

public class AccountVerificationServiceMySQL implements AccountVerificationService{

    private final AccountRepository accountRepository;

    public AccountVerificationServiceMySQL(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Notification<Boolean> saveAccount(String type, Long money) {
        Account account = new AccountBuilder()
                .setType(type)
                .setAmountOfMoney(money)
                .setDateOfCreation(new Date())
                .build();
        AccountClientValidator accountClientValidator = new AccountClientValidator(account);
        boolean clientValid = accountClientValidator.validate();
        Notification<Boolean> accountCreationNotification = new Notification<>();

        if(!clientValid){
            accountClientValidator.getErrors().forEach(accountCreationNotification::addError);
            accountCreationNotification.setResult(Boolean.FALSE);
        } else {
            accountCreationNotification.setResult(accountRepository.saveAccount(account));
        }

        return accountCreationNotification;
    }

    @Override
    public Notification<Boolean> deleteAccount(Long id) throws EntityNotFoundException {
        Account account = new AccountBuilder()
                .setId(id)
                .setDateOfCreation(new Date())
                .build();
        DeleteAccountValidator deleteAccountValidator = new DeleteAccountValidator(account, ComponentFactory.instance(false));
        boolean deleteValid = deleteAccountValidator.validate();
        Notification<Boolean> deleteNotification = new Notification<>();

        if(!deleteValid){
            deleteAccountValidator.getErrors().forEach(deleteNotification::addError);
            deleteNotification.setResult(Boolean.FALSE);
        } else {
            deleteNotification.setResult(accountRepository.deleteAccount(account.getId()));
        }
        return deleteNotification;
    }

    @Override
    public Notification<Boolean> updateAccount(Account account) throws EntityNotFoundException {
        UpdateAccountValidator updateAccountValidator = new UpdateAccountValidator(account, ComponentFactory.instance(false));
        boolean updateValid = updateAccountValidator.validate();
        Notification<Boolean> updateNotification = new Notification<>();

        if(!updateValid){
            updateAccountValidator.getErrors().forEach(updateNotification::addError);
            updateNotification.setResult(Boolean.FALSE);
        } else {
            updateNotification.setResult(accountRepository.updateAccount(account));
        }
        return updateNotification;
    }

    @Override
    public Notification<Boolean> transferMoney(Account account1, Account account2, Long money){
        TransferMoneyValidator transferMoneyValidator = new TransferMoneyValidator(account1, money);
        boolean transferValid = transferMoneyValidator.validate();
        Notification<Boolean> transferNotification = new Notification<>();

        if(!transferValid){
            transferMoneyValidator.getErrors().forEach(transferNotification::addError);
            transferNotification.setResult(Boolean.FALSE);
        } else {
            transferNotification.setResult(accountRepository.transferMoney(account1, account2, money));
        }
        return transferNotification;
    }

}
