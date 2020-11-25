package controller.account;

import factory.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import model.validation.Notification;
import repository.account.AccountRepository;
import service.account.AccountVerificationService;
import view.account.ClientAccountView;
import view.account.UpdateAccountView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class UpdateAccountController {

    private final UpdateAccountView updateAccountView;
    private final AccountVerificationService accountVerificationService;

    public UpdateAccountController(UpdateAccountView updateAccountView, ComponentFactory componentFactory) throws HeadlessException {
        this.updateAccountView = updateAccountView;
        this.accountVerificationService = componentFactory.getAccountVerificationService();
        updateAccountView.setBackButtonListener(new UpdateAccountController.BackButtonListener());
        updateAccountView.setSubmitButtonListener(new UpdateAccountController.SubmitButtonListener());
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            updateAccountView.dispose();
            new ClientAccountController(new ClientAccountView());

        }
    }

    private class SubmitButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Long id = Long.parseLong(updateAccountView.getId());
            String type = updateAccountView.getAccType();
            String amountOfMoney = updateAccountView.getAmountOfMoney();

            Account account;
            if (amountOfMoney.equals("")) {
                account = new AccountBuilder()
                        .setId(id)
                        .setType(type)
                        .setDateOfCreation(new Date())
                        .build();
            } else {
                account = new AccountBuilder()
                        .setId(id)
                        .setType(type)
                        .setAmountOfMoney(Long.parseLong(amountOfMoney))
                        .setDateOfCreation(new Date())
                        .build();
            }

            Notification<Boolean> updateNotification = null;
            try {
                updateNotification = accountVerificationService.updateAccount(account);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            if (updateNotification != null) {
                if (updateNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(updateAccountView.getContentPane(), updateNotification.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(updateAccountView.getContentPane(), "Data updated!");
                }
            }
        }
    }
}
