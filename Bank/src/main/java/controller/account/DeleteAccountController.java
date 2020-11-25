package controller.account;

import factory.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import model.validation.Notification;
import repository.account.AccountRepository;
import service.account.AccountVerificationService;
import view.account.ClientAccountView;
import view.account.DeleteAccountView;
import view.account.UpdateAccountView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class DeleteAccountController {

    private final DeleteAccountView deleteAccountView;
    //private final AccountRepository accountRepository;
    private final AccountVerificationService accountVerificationService;

    public DeleteAccountController(DeleteAccountView deleteAccountView, ComponentFactory componentFactory) throws HeadlessException {
        this.deleteAccountView = deleteAccountView;
        //this.accountRepository = componentFactory.getAccountRepository();
        this.accountVerificationService = componentFactory.getAccountVerificationService();
        deleteAccountView.setBackButtonListener(new DeleteAccountController.BackButtonListener());
        deleteAccountView.setSubmitButtonListener(new DeleteAccountController.SubmitButtonListener());
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            deleteAccountView.dispose();
            new ClientAccountController(new ClientAccountView());

        }
    }

    private class SubmitButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            Long id = Long.parseLong(deleteAccountView.getId());

            Notification<Boolean> deleteNotification = null;
            try{
                deleteNotification = accountVerificationService.deleteAccount(id);
            } catch (Exception e1){
                e1.printStackTrace();
            }

            if(deleteNotification != null){
                if(deleteNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(deleteAccountView.getContentPane(), deleteNotification.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(deleteAccountView.getContentPane(), "Data deleted!");
                }
            }
        }
    }
}
