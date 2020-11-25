package controller.account;

import factory.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import model.validation.Notification;
import repository.account.AccountRepository;
import service.account.AccountVerificationService;
import view.account.ClientAccountView;
import view.account.CreateAccountView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CreateAccountController {

    private final CreateAccountView createAccountView;
    private final AccountVerificationService accountVerificationService;

    public CreateAccountController(CreateAccountView createAccountView, ComponentFactory componentFactory) throws HeadlessException {
        this.createAccountView = createAccountView;
        this.accountVerificationService = componentFactory.getAccountVerificationService();
        createAccountView.setBackButtonListener(new BackButtonListener());
        createAccountView.setSubmitButtonListener(new SubmitButtonListener());
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            createAccountView.dispose();
            new ClientAccountController(new ClientAccountView());

        }
    }

    private class SubmitButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            String type = createAccountView.getAccType();
            String amountOfMoney = createAccountView.getAmountOfMoney();

            Notification<Boolean> accountNotification = null;
            try{
                accountNotification = accountVerificationService.saveAccount(type, Long.parseLong(amountOfMoney));
            } catch (Exception e1){
                e1.printStackTrace();
            }

            if(accountNotification != null){
                if(accountNotification.hasErrors()){
                    JOptionPane.showMessageDialog(createAccountView.getContentPane(), accountNotification.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(createAccountView.getContentPane(), "Data saved!");
                }
            }
        }
    }
}
