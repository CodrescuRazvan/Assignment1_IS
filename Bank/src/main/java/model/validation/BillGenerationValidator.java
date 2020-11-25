package model.validation;

import model.Account;

import java.util.ArrayList;
import java.util.List;

public class BillGenerationValidator {

    private static final int LENGTH_AMOUNT_OF_MONEY = 10;

    private final List<String> errors;
    private final Account account;
    private final Long water;
    private final Long gas;
    private final Long electricity;

    public BillGenerationValidator(Account account, Long water, Long gas, Long electricity) {
        this.account = account;
        this.water = water;
        this.electricity = electricity;
        this.gas = gas;
        errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate(){
        validateBill(account.getAmountOfMoney(), water, gas, electricity);
        validateMoney(water.toString());
        validateMoney(gas.toString());
        validateMoney(electricity.toString());
        return errors.isEmpty();
    }

    private void validateBill(Long money1, Long water, Long gas, Long electricity){
        if(money1 < water + gas + electricity){
            errors.add("Insufficient money for all bills!");
        }
        /*if(money1 < gas){
            errors.add("Insufficient money for gas bill!");
        }
        if(money1 < electricity){
            errors.add("Insufficient money for electricity bill!");
        }*/

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
