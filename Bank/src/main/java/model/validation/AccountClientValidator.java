package model.validation;

import model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountClientValidator {

    private static final int LENGTH_AMOUNT_OF_MONEY = 10;

    private final List<String> errors;
    private final Account account;

    public AccountClientValidator(Account account) {
        this.account = account;
        errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate(){
        validateType(account.getType());
        validateMoney(account.getAmountOfMoney().toString());
        return errors.isEmpty();
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
