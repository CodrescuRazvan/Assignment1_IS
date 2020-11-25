package model.validation;

import factory.ComponentFactory;
import model.Account;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class DeleteAccountValidator {

    private final List<String> errors;
    private final Account account;
    private final ComponentFactory componentFactory;

    public DeleteAccountValidator(Account account, ComponentFactory componentFactory) {
        errors = new ArrayList<>();
        this.account = account;
        this.componentFactory = componentFactory;
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate() throws EntityNotFoundException {
        validateId(account.getId());
        return errors.isEmpty();
    }

    private void validateId(Long id) throws EntityNotFoundException {
        AccountRepository accountRepository = componentFactory.getAccountRepository();
        Account account = accountRepository.findById(id);
        if(account.getId() == null){
                errors.add("ID not found");
        }
    }
}
