package model.builder;

import model.Account;
import model.Client;
import model.Role;

import java.util.Date;

public class AccountBuilder {

    private Account account;

    public AccountBuilder() {
        account = new Account();
    }

    public AccountBuilder setId(Long id) {
        account.setId(id);
        return this;
    }

    public AccountBuilder setType(String type) {
        account.setType(type);
        return this;
    }

    public AccountBuilder setAmountOfMoney(Long amountOfMoney) {
        account.setAmountOfMoney(amountOfMoney);
        return this;
    }

    public AccountBuilder setDateOfCreation(Date date) {
        account.setDateOfCreation(date);
        return this;
    }

    public Account build() {
        return account;
    }
}
