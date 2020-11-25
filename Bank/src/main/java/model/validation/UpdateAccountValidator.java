package model.validation;

import factory.ComponentFactory;
import model.Account;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class UpdateAccountValidator {

    private final List<String> errors;
    private final Account account;
    private final ComponentFactory componentFactory;
    private static final int LENGTH_AMOUNT_OF_MONEY = 10;

    public UpdateAccountValidator(Account account, ComponentFactory componentFactory) {
        errors = new ArrayList<>();
        this.account = account;
        this.componentFactory = componentFactory;
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate() throws EntityNotFoundException {
        validateId(account.getId());
        validateType(account.getType());
        validateMoney(account.getAmountOfMoney().toString());
        return errors.isEmpty();
    }

    private void validateId(Long id) throws EntityNotFoundException {
        AccountRepository accountRepository = componentFactory.getAccountRepository();
        Account account = accountRepository.findById(id);
        if(account.getId() == null){
            errors.add("ID not found");
        }
    }

    private void validateType(String type){
        if(!type.equals("checkings") && !type.equals("savings")){
            errors.add("Invalid Account Type!");
        }
    }

    private void validateMoney(String money){
        if(money.length() > LENGTH_AMOUNT_OF_MONEY){
            errors.add("Can't have that much money!");
        }
        if(containsChar(money)){
            errors.add("Money value can't contain letters!");
        }
    }

    private boolean containsChar(String s) {
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return true;
                }
            }
        }
        return false;
    }
}
