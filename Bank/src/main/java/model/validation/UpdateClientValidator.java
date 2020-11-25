package model.validation;

import model.Client;

import java.util.ArrayList;
import java.util.List;

public class UpdateClientValidator {

    private static final int PNC_LENGTH = 10;
    private static final int CARD_NUMBER_LENGTH = 10;

    private final List<String> errors;
    private final Client client;

    public UpdateClientValidator(Client client) {
        this.client = client;
        errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate(){
        validatePNC(client.getPNC().toString());
        validateName(client.getName());
        validateCardNumber(client.getCardNumber());
        return errors.isEmpty();
    }

    private void validatePNC(String PNC){
        if(PNC.length() != PNC_LENGTH){
            errors.add("Invalid PNC Length!");
        }
        if(containsChar(PNC)){
            errors.add("PNC can't contain letters!");
        }
    }

    private void validateName(String name){
        if(containsDigit(name)){
            errors.add("Name should not contain numbers!");
        }
    }

    private void validateCardNumber(String cardNumber){
        if(cardNumber.length() != CARD_NUMBER_LENGTH && !cardNumber.equals("")){
            errors.add("Invalid Card Number Length!");
        }
        if(containsChar(cardNumber)){
            errors.add("Card number can't contain letters!");
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

    private boolean containsDigit(String s) {
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    return true;
                }
            }
        }
        return false;
    }
}
