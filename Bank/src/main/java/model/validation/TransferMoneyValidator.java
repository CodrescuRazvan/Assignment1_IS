package model.validation;

import model.Account;

import java.util.ArrayList;
import java.util.List;

public class TransferMoneyValidator {

    private static final int LENGTH_AMOUNT_OF_MONEY = 10;

    private final List<String> errors;
    private final Account account;
    private final Long money;

    public TransferMoneyValidator(Account account, Long money) {
        this.account = account;
        this.money = money;
        errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate(){
        validateTransfer(account.getAmountOfMoney(), money);
        validateMoney(money.toString());
        return errors.isEmpty();
    }

    private void validateTransfer(Long money1, Long money){
        if(money1 < money){
            errors.add("Insuficient money in account!");
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
