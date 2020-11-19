package controller.account;

import factory.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import repository.account.AccountRepository;
import view.account.ClientAccountView;
import view.account.DeleteAccountView;
import view.account.UpdateAccountView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class DeleteAccountController {

    private final DeleteAccountView deleteAccountView;
    private final AccountRepository accountRepository;

    public DeleteAccountController(DeleteAccountView deleteAccountView, ComponentFactory componentFactory) throws HeadlessException {
        this.deleteAccountView = deleteAccountView;
        this.accountRepository = componentFactory.getAccountRepository();
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
            accountRepository.deleteAccount(id);
        }
    }
}
